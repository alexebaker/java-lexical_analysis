public class IdentifierToken extends Token {
    IdentifierToken(String value, CompilerState cs) {
        super("$id", value, cs);
    }

    /**
     * Checks if ch is a valid Identifier Token
     * @param str string to check if it is an Identifier token
     * @return true if ch is an Identifier Token, false otherwise
     */
    public static boolean isToken(String str) {
        return !KeywordToken.isToken(str) && str.matches("^[a-zA-Z_]+[a-zA-Z0-9_]*");
    }
}
