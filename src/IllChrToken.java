public class IllChrToken extends Token {
    IllChrToken(String value, CompilerState cs) {
        super("$illchr", value, cs);
    }

    public static boolean isToken(String str) {
        return !LiteralToken.isToken(str) && !KeywordToken.isToken(str) && !IdentifierToken.isToken(str) && !NumberToken.isToken(str);
    }
}
