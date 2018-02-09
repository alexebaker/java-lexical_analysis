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

    /**
     * Gets the CompilerIO object for reading and writing
     *
     * @return the Compiler IO object
     */
    public CompilerIO getIO() {
        return this.io;
    }

    /**
     * Gets the current line number in the file being compiled
     *
     * @return Current line number
     */
    public int getLineCount() {
        return this.lineCount;
    }

    /**
     * Get the current char count for the given line
     *
     * @return The current char count
     */
    public int getCharCount() {
        return this.charCount;
    }

    /**
     * Path the the file or input being read from
     *
     * @return Path to input stream
     */
    public String getInputPath() {
        return this.inputPath;
    }

    /**
     * Reads a byte from the input. This will also increment the
     * line count and char count as needed.
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
