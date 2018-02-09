import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Alexander Baker
 *
 * CS 554
 * Lexical Analysis
 */

public class CompilerState {
    private String inputPath;
    private CompilerIO io;

    private int lineCount = 1;
    private int charCount = 0;

    CompilerState() {
        this.inputPath = "<stdin>";
        this.io = new CompilerIO();
    }

    CompilerState(String fileName) {
        this.inputPath = fileName;

        try {
            this.io = new CompilerIO(new FileReader(fileName), System.out);
        }
        catch (FileNotFoundException ex) {
            System.err.println("Could not find file: " + fileName);
            System.err.println(ex);
            System.exit(1);
        }
    }

    public CompilerIO getIO() {
        return this.io;
    }

    public int getLineCount() {
        return this.lineCount;
    }

    public int getCharCount() {
        return this.charCount;
    }

    public String getInputPath() {
        return this.inputPath;
    }

    /**
     * Reads a byte from the input.
     *
     * @return the read byte
     */
    public int read() {
        int ch = this.io.read();

        this.charCount += 1;
        if (ch == '\n') {
            this.lineCount += 1;
            this.charCount = 0;
        }
        return ch;
    }
}
