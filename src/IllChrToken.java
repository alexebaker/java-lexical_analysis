public class IllChrToken extends Token {
    IllChrToken(String value, CompilerState cs) {
        super("$illchr", value, cs);
    }

    /**
     * Checks if ch is a valid Illegal Character Token
     * @param str string to check if it is an Illegal Character token
     * @return true if str is an Illegal Character Token, false otherwise
     */
    public static boolean isToken(String str) {
        return !LiteralToken.isToken(str) && !KeywordToken.isToken(str) && !IdentifierToken.isToken(str) && !NumberToken.isToken(str);
    }
}
