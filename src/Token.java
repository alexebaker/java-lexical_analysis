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

    Token() {
        this("", "", new CompilerState());
    }

    Token(String token, CompilerState cs) {
        this(token, "", cs);
    }

    Token(String token, String value, CompilerState cs) {
        this.token = token;
        this.value = value;
        this.location = cs.getInputPath();
        this.lineCount = cs.getLineCount();
        if (value.length() > 0) {
            this.charCount = cs.getCharCount() - value.length() + 1;
        }
        else {
            this.charCount = cs.getCharCount() - token.length() + 1;
        }
    }

    public String toString() {
        String str = location + ":" + lineCount + ":" + charCount + ":" + token;
        if (this.value.length() > 0) {
            str += ":" + this.value;
        }
        return str;
    }
}
