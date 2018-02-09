public class NumberToken extends Token {
    NumberToken(String value, CompilerState cs) {
        super("$num", value, cs);
    }

    /**
     * Checks if ch is a valid Number Token
     * @param str string to check if it is an Number token
     * @return true if ch is a Number Token, false otherwise
     */
    public static boolean isToken(String str) {
        return str.matches("^[0-9]+\\.?[0-9]*");
    }
}
