public class EOFToken extends Token {
    public EOFToken(CompilerState cs) {
        super("$EOF", cs);
    }

    /**
     * Checks if ch is a valid EOF Token
     * @param ch character to check if it is an EOF token
     * @return true if ch is an EOF token, false otherwise
     */
    public static boolean isToken(int ch) {
        return ch == -1;
    }
}
