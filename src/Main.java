import day01.Day01;
import day02.Day02;
import day03.Day03;

public class Main {
    /**
     * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even
     * given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
     *
     * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by
     * December 25th.
     *
     * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the
     * second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Advent of Code 2023");
        System.out.printf("DAY 01  *: %d%n", Day01.puzzle1());
        System.out.printf("DAY 01 **: %d%n", Day01.puzzle2());
        System.out.printf("DAY 02  *: %d%n", Day02.puzzle1());
        System.out.printf("DAY 02 **: %d%n", Day02.puzzle2());
        System.out.printf("DAY 03  *: %d%n", Day03.puzzle1());
        System.out.printf("DAY 03 **: %d%n", Day03.puzzle2());
    }
}