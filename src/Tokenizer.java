import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

public class Tokenizer {
    CompilerState cs;
    int bufferCapacity = 1024;

    public Tokenizer(CompilerState cs) {
        this.cs = cs;
    }

    public void tokenize() {
        int ch;
        boolean inComment = false;
        CharBuffer charBuffer = CharBuffer.allocate(this.bufferCapacity);

        while (true) {
            ch = this.cs.read();
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

            if (isComment(buf)) {
                inComment = true;
                charBuffer = CharBuffer.allocate(this.bufferCapacity);
            }
            else if (LiteralToken.isToken(buf)) {
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

    private boolean isDelim(int ch) {
        return EOFToken.isToken(ch) || Character.isWhitespace(ch) || IllChrToken.isToken(Character.toString((char) ch));
    }

    private boolean isNumDelim(String str) {
        boolean literalToken;
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

    private boolean isComment(String str) {
        return str.equals("//");
    }
}
