import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

public class Tokenizer {
    CompilerState cs;
    int bufferCapacity = 1024;

    public Tokenizer(CompilerState cs) {
        this.cs = cs;
    }

    /**
     * Tokenizes the input based on what is read from the compiler state
     * and prints the tokens to the output from compiler state.
     */
    public void tokenize() {
        int ch;
        boolean inComment = false;
        CharBuffer charBuffer = CharBuffer.allocate(this.bufferCapacity);

        while (true) {
            ch = this.cs.read();

            // First, determine special cases that to not need to be tokenized
            if (EOFToken.isToken(ch)) {
                this.cs.getIO().write(new EOFToken(this.cs).toString());
                break;
            }
            else if (ch == '\n') {
                inComment = false;
                continue;
            }
            else if (Character.isWhitespace(ch) || inComment) {
                continue;
            }

            try {
                charBuffer.append((char) ch);
            }
            catch (BufferOverflowException ex) {
                System.err.println("Token exceeded " + this.bufferCapacity + " bytes.");
                System.err.println(ex);
                System.exit(1);
            }

            String buf = new String(charBuffer.array()).trim();
            int nextCh = this.cs.getIO().peek();
            String nextToken = Character.toString((char) nextCh);

            // Determine if the token in valid. The inside if statements determines
            // if the token needs to be written to the output stream
            if (isComment(buf)) {
                inComment = true;
                charBuffer = CharBuffer.allocate(this.bufferCapacity);
            }
            else if (LiteralToken.isToken(buf)) {
                // The last check for a number token handles the case when a '.' is a literal token and not part of a number
                if (isDelim(nextCh) || !LiteralToken.isToken(buf+nextToken) && !isComment(buf+nextToken) || NumberToken.isToken(nextToken)) {
                    this.cs.getIO().write(new LiteralToken(buf, cs).toString());
                    charBuffer = CharBuffer.allocate(this.bufferCapacity);
                }
            }
            else if (KeywordToken.isToken(buf)) {
                if (isDelim(nextCh) || LiteralToken.isToken(nextToken)) {
                    this.cs.getIO().write(new KeywordToken(buf, cs).toString());
                    charBuffer = CharBuffer.allocate(this.bufferCapacity);
                }
            }
            else if (IdentifierToken.isToken(buf)) {
                if (isDelim(nextCh) || LiteralToken.isToken(nextToken)) {
                    this.cs.getIO().write(new IdentifierToken(buf, cs).toString());
                    charBuffer = CharBuffer.allocate(this.bufferCapacity);
                }
            }
            else if (NumberToken.isToken(buf)) {
                // The last check catches numbers which would have more than one '.' in it. If the next token is a '.',
                // Then is will print out this number and treat the next '.' as a literal token
                if (isDelim(nextCh) || isNumDelim(nextToken) || !NumberToken.isToken(buf+nextToken)) {
                    this.cs.getIO().write(new NumberToken(buf, cs).toString());
                    charBuffer = CharBuffer.allocate(this.bufferCapacity);
                }
            }
            else {
                this.cs.getIO().write(new IllChrToken(buf, cs).toString());
                charBuffer = CharBuffer.allocate(this.bufferCapacity);
            }
        }
    }

    /**
     * Basic delimiter check for all tokens
     *
     * @param ch Character to check if it is a delimiter or not
     * @return true if ch is a delimiter, false otherwise
     */
    private boolean isDelim(int ch) {
        return EOFToken.isToken(ch) || Character.isWhitespace(ch) || IllChrToken.isToken(Character.toString((char) ch));
    }

    /**
     * Special delimiter cases fro numbers.
     *
     * @param str String to check if it deliminates a number
     * @return true if str is a number delimiter, false otherwise
     */
    private boolean isNumDelim(String str) {
        boolean literalToken;

        // Have to check for a literal token EXCEPT for '.'. If a check using LiteralToken.isToken is used, then numbers
        // with a '.' in them will be broken into two numbers with a literal token between them.
        switch (str) {
            case "(":
            case ")":
            case "[":
            case "]":
            case "{":
            case "}":
            case ",":
            case ";":
            case ":":
            case "::":
            case "!":
            case "?":
            case "=":
            case "==":
            case "!=":
            case "<<":
            case ">>":
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "&":
            case "&&":
            case "|":
            case "||":
            case "^":
            case "*":
            case "%":
            case "/":
            case "+":
            case "-":
            case "++":
            case "--":
                literalToken = true;
                break;
            default:
                literalToken = false;
        }

        return literalToken || KeywordToken.isToken(str) || IdentifierToken.isToken(str);
    }

    /**
     * Checks for comments
     *
     * @param str string to check if it is a comment token
     * @return true if str is a comment token, false otherwise
     */
    private boolean isComment(String str) {
        return str.equals("//");
    }
}
