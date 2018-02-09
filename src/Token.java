/**
 * @author Alexander Baker
 *
 * CS 554
 * Lexical Analysis
 */

public class Token {
    private String token;
    private String value;
    private String location;
    private int lineCount;
    private int charCount;

    Token(String token, CompilerState cs) {
        this(token, "", cs);
    }

    Token(String token, String value, CompilerState cs) {
        this.token = token;
        this.value = value;
        this.location = cs.getInputPath();
        this.lineCount = cs.getLineCount();

        // Kinda a hack, but used to get the correct char position for the different tokens.
        // Need to subtract off the length of the token, since it is printed after the entire
        // token is read, so char position will be at the end of the token and not the beginning.
        if (token.equals("$EOF")) {
            this.charCount = cs.getCharCount();
        }
        else if (value.length() > 0) {
            this.charCount = cs.getCharCount() - value.length() + 1;
        }
        else {
            this.charCount = cs.getCharCount() - token.length() + 1;
        }
    }

    @Override
    public String toString() {
        String str = location + ":" + lineCount + ":" + charCount + ":" + token;
        if (this.value.length() > 0) {
            str += ":" + this.value;
        }
        return str;
    }
}
