package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Loads an input file of any puzzle. Each puzzle has to be saved in its own package named day_XX, where XX is the day
 * number. The input file must be named "input.txt".
 */
public class InputFile {
    private final String SOURCE_DIR_NAME = "src";
    private final String INPUT_FILE_NAME = "input.txt";
    private Scanner SC;

    /**
     * Constructs input file path out of caller class name. Initializes Scanner for reading the file.
     */
    public InputFile() {
        String packageName = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.")[0];
        String filePath = String.valueOf(Paths.get(this.SOURCE_DIR_NAME, packageName, this.INPUT_FILE_NAME));
        try {
            this.SC = new Scanner(new File(filePath));
            this.SC.useDelimiter("\n");
        } catch(FileNotFoundException ignored) {
            System.out.printf("%s: Cannot find input file: \"%s\".%n", packageName.toUpperCase(), filePath);
        }
    }

    /**
     * @return true if there is an unread line in the input file, false otherwise
     */
    public boolean hasNextLine() {
        try {
            return this.SC.hasNext();
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * @return the next unread line from the input file or null if the end of the file has been reached already
     */
    public String getNextLine() {
        if(this.SC.hasNext()) {
            return this.SC.next();
        } else {
            this.SC.close();
            return null;
        }
    }
}
