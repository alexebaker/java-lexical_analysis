
public class LiteralToken extends Token {
    LiteralToken(String token, CompilerState cs) {
        super(token, cs);
    }

    public static boolean isToken(String str) {
        switch (str) {
            case "(":
            case ")":
            case "[":
            case "]":
            case "{":
            case "}":
            case ".":
            case ",":
            case ";":
            case ":":
            case "::":
            case "!":
            case "?":
            case "=":
            case "==":
            case "!=":
            case "<<":
            case ">>":
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "&":
            case "&&":
            case "|":
            case "||":
            case "^":
            case "*":
            case "%":
            case "/":
            case "+":
            case "-":
            case "++":
            case "--":
                return true;
            default:
                return false;
        }
    }
}
