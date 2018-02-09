public class KeywordToken extends Token {
    KeywordToken(String token, CompilerState cs) {
        super(token, cs);
    }

    /**
     * Checks if ch is a valid Keyword Token
     * @param str string to check if it is an Keyword token
     * @return true if ch is an Keyword Token, false otherwise
     */
    public static boolean isToken(String str) {
        switch (str) {
            case "bool":
            case "break":
            case "case":
            case "continue":
            case "default":
            case "do":
            case "else":
            case "false":
            case "float":
            case "if":
            case "return":
            case "signed":
            case "static":
            case "struct":
            case "switch":
            case "true":
            case "unsigned":
            case "var":
            case "void":
            case "while":
                return true;
            default:
                return false;
        }
    }
}
