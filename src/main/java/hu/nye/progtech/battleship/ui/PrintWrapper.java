package hu.nye.progtech.battleship.ui;

/**
 * It's a Util class to wrap print operations.
 */
public class PrintWrapper {

    /**
     * Prints a line to stdout, helps the Sysout.
     *
     * @param string the input string to print.
     */
    public void printLine(String string) {
        System.out.println(string);
    }

    /**
     * Prints a line without line break.
     *
     * @param string the user string to output.
     */
    public void print(String string) {
        System.out.print(string);
    }

}