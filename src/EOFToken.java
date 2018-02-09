public class EOFToken extends Token {
    public EOFToken(CompilerState cs) {
        super("$EOF", cs);
    }

    public static boolean isToken(int ch) {
        return ch == -1;
    }
}
