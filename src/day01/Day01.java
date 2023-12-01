package day01;

import utils.InputFile;

import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 1: Trebuchet?! ---
 *
 * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you
 * ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the
 * sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into
 * a trebuchet ("please hold still, we need to strap you in").
 */
public class Day01 {

    /**
     * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has
     * been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the
     * Elves are having trouble reading the values on the document.
     *
     * The newly-improved calibration document consists of lines of text; each line originally contained a specific
     * calibration value that the Elves now need to recover. On each line, the calibration value can be found by
     * combining the first digit and the last digit (in that order) to form a single two-digit number.
     *
     * For example:
     * 1abc2
     * pqr3stu8vwx
     * a1b2c3d4e5f
     * treb7uchet
     *
     * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together
     * produces 142.
     *
     * @return Consider your entire calibration document. What is the sum of all of the calibration values?
     */
    public static int puzzle1() {
        InputFile inputFile = new InputFile();
        int sum = 0;
        Map<String, Character> digitsMap = new HashMap<>();

        for(int i = 1; i < 10; i++) {
            digitsMap.put(String.valueOf(i), Integer.toString(i).charAt(0));
        }

        while(inputFile.hasNextLine()) {
            sum += getCalibrationValue(inputFile.getNextLine(), digitsMap);
        }

        return sum;
    }

    /**
     * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one,
     * two, three, four, five, six, seven, eight, and nine also count as valid "digits".
     *
     * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
     * two1nine
     * eightwothree
     * abcone2threexyz
     * xtwone3four
     * 4nineeightseven2
     * zoneight234
     * 7pqrstsixteen
     *
     * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
     *
     * @return What is the sum of all of the calibration values?
     */
    public static int puzzle2() {
        InputFile inputFile = new InputFile();
        int sum = 0;
        Map<String, Character> digitsMap = new HashMap<>();
        String[] spelledOut = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for(int i = 0; i < spelledOut.length; i++) {
            char numberChar = Integer.toString(i + 1).charAt(0);
            digitsMap.put(spelledOut[i], numberChar);
            digitsMap.put(new StringBuilder(spelledOut[i]).reverse().toString(), numberChar);
            digitsMap.put(String.valueOf(i + 1), numberChar);
        }

        while(inputFile.hasNextLine()) {
            sum += getCalibrationValue(inputFile.getNextLine(), digitsMap);
        }

        return sum;
    }

    /**
     *
     * @param line a line from the calibration document
     * @param digitsMap conversions between substrings and digit characters
     * @return first digit found in the line or char equivalent of null (does not happen with Advent of Code input)
     */
    private static char getFirstDigit(String line, Map<String, Character> digitsMap) {
        for(int i = 0; i < line.length(); i++) {
            for(String key : digitsMap.keySet()) {
                if(line.startsWith(key, i)) {
                    return digitsMap.get(key);
                }
            }
        }
        return Character.MIN_VALUE;
    }

    /**
     * @param line a line from the calibration document
     * @param digitsMap conversions between substrings and digit characters
     * @return the line calibration value
     */
    private static int getCalibrationValue(String line, Map<String, Character> digitsMap) {
        char firstDigit = getFirstDigit(line, digitsMap);
        char secondDigit = getFirstDigit(new StringBuilder(line).reverse().toString(), digitsMap);
        return Integer.parseInt(String.valueOf(new char[] {firstDigit, secondDigit}));
    }
}
