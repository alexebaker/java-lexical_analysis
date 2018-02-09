public class IdentifierToken extends Token {
    IdentifierToken(String value, CompilerState cs) {
        super("$id", value, cs);
    }

    public static boolean isToken(String str) {
        return !KeywordToken.isToken(str) && str.matches("^[a-zA-Z_]+[a-zA-Z0-9_]*");
    }
}
