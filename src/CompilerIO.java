import java.io.*;

/**
 * @author Alexander Baker
 *
 * CS 554
 * Lexical Analysis
 */

public class CompilerIO {
    private BufferedReader in;
    private BufferedWriter out;

    CompilerIO() {
        this(System.in, System.out);
    }

    CompilerIO(InputStream in, OutputStream out) {
       this.in = new BufferedReader(new InputStreamReader(in));
       this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    CompilerIO(FileReader in, FileWriter out) {
       this.in = new BufferedReader(in);
       this.out = new BufferedWriter(out);
    }

    CompilerIO(InputStream in, FileWriter out) {
       this.in = new BufferedReader(new InputStreamReader(in));
       this.out = new BufferedWriter(out);
    }

    CompilerIO(FileReader in, OutputStream out) {
       this.in = new BufferedReader(in);
       this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    /**
     * Reads a byte from the input.
     *
     * @return the read byte
     */
    public int read() {
        int ch = -1;

        try {
            ch = this.in.read();
        }
        catch (IOException ex) {
            System.err.println("Failed to read from input stream:");
            System.err.println(ex);
            System.exit(1);
        }
        return ch;
    }

    /**
     * Shows the next byte in the input without reading it.
     *
     * @return the next byte in the input
     */
    public int peek() {
        return peek(1);
    }

    /**
     * Gets the nth byte from the current position in the input.
     *
     * @param readAheadLimit number of bytes to read ahead
     * @return the nth byte in the input
     */
    public int peek(int readAheadLimit) {
        int ch = -1;

        try {
            this.in.mark(readAheadLimit);

            for (int i = 0; i < readAheadLimit; i++) {
                ch = read();
            }

            this.in.reset();
        }
        catch (IOException ex) {
            System.err.println("Failed to read ahead " + readAheadLimit + "from input stream:");
            System.err.println(ex);
            System.exit(1);
        }
        return ch;
    }

    /**
     * Calls the builtin Reader method, identifies if peek can be used.
     *
     * @return true if mark is supported, false otherwise
     */
    public boolean markSupported() {
        return this.in.markSupported();
    }

    /**
     * Writes the character or string to the output buffer
     *
     * @param ch Character to write
     */
    public void write(int ch) {
        write((char) ch);
    }

    /**
     * Writes the character or string to the output buffer
     *
     * @param ch Character to write
     */
    public void write(char ch) {
        write(Character.toString(ch));
    }

    /**
     * Writes the character or string to the output buffer
     *
     * @param str String to write
     */
    public void write(String str) {
        try {
            this.out.write(str);
            this.out.newLine();
            this.out.flush();
        }
        catch (IOException ex) {
            System.err.println("Failed to write \"" + str + "\" to the output stream.");
            System.err.println(ex);
            System.exit(1);
        }
    }

    /**
     * Closes the input reader and output writer
     */
    public void close() {
        try {
            this.in.close();
            this.out.close();
        }
        catch (IOException ex) {
            System.err.println("Failed to close the input or output stream.");
            System.err.println(ex);
            System.exit(1);
        }
    }
}
