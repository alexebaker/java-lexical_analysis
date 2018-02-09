public class NumberToken extends Token {
    NumberToken(String value, CompilerState cs) {
        super("$num", value, cs);
    }

    public static boolean isToken(String str) {
        return str.matches("^[0-9]+\\.?[0-9]*");
    }
}
