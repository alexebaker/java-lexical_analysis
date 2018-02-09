/**
 * @author Alexander Baker
 *
 * CS 554
 * Lexical Analysis
 */

public class Main {
    public static void main(String[] argv) {
        if (argv.length > 1) {
            System.err.println("Too many arguments, can only give 0 or 1 argument.");
            System.exit(1);
        }

        System.setProperty("line.separator", "\n");
        CompilerState cs = new CompilerState();

        if (argv.length == 1) {
            cs = new CompilerState(argv[0]);
        }

        Tokenizer tokenizer = new Tokenizer(cs);
        tokenizer.tokenize();

        cs.getIO().close();
        System.exit(0);
    }
}
