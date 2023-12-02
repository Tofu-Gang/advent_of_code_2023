package day02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * As you walk, the Elf shows you a small bag and some cubes which are either red, green, or blue. Each time you play
 * this game, he will hide a secret number of cubes of each color in the bag, and your goal is to figure out information
 * about the number of cubes.
 */
public class Game {
    private final int ID;
    private final ArrayList<GameStep> gameSteps = new ArrayList<>();
    private final int minRed;
    private final int minBlue;
    private final int minGreen;


    /**
     * Initialize game ID and all game steps; count the fewest number of cubes of each color that could have been in the
     * bag to make the game possible.
     * @param data a line from the input file
     */
    public Game(String data) {
        Matcher idMatcher = Pattern.compile("^Game ([0-9]+)").matcher(data);
        this.ID = idMatcher.find() ? Integer.parseInt(idMatcher.group(1)) : -1;

        Matcher gameStepsMatcher = Pattern.compile("^Game [0-9]+: (.*)$").matcher(data);
        String steps = gameStepsMatcher.find() ? gameStepsMatcher.group(1) : null;
        for(String step : steps.split(";")) {
            this.gameSteps.add(new GameStep(step));
        }

        this.minRed = Collections.max(this.gameSteps.stream().map(GameStep::getRedCount).toList());
        this.minBlue = Collections.max(this.gameSteps.stream().map(GameStep::getBlueCount).toList());
        this.minGreen = Collections.max(this.gameSteps.stream().map(GameStep::getGreenCount).toList());
    }

    /**
     * @return game ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @return true if this game would be possible given cube count limits for puzzle 1, false otherwise
     */
    public boolean isPossible() {
        return this.gameSteps.stream().map(GameStep::isPossible).toList().stream().allMatch(Boolean.TRUE::equals);
    }

    /**
     * @return the minimum possible numbers of red, green, and blue cubes multiplied together
     */
    public int power() {
        return this.minRed * this.minBlue * this.minGreen;
    }
}

/**
 * Subsets of cubes that were revealed from the bag during one game step.
 */
class GameStep {
    private final int redCount;
    private final int blueCount;
    private final int greenCount;
    // cube count limits for puzzle 1
    private final int RED_LIMIT = 12;
    private final int BLUE_LIMIT = 14;
    private final int GREEN_LIMIT = 13;

    /**
     * Initialize numbers of red, blue and green cubes that have been revealed during this game step.
     * @param data game step string from the input file
     */
    public GameStep(String data) {
        Matcher redMatcher = Pattern.compile("([0-9]+) red").matcher(data);
        Matcher blueMatcher = Pattern.compile("([0-9]+) blue").matcher(data);
        Matcher greenMatcher = Pattern.compile("([0-9]+) green").matcher(data);
        this.redCount = redMatcher.find() ? Integer.parseInt(redMatcher.group(1)) : 0;
        this.blueCount = blueMatcher.find() ? Integer.parseInt(blueMatcher.group(1)) : 0;
        this.greenCount = greenMatcher.find() ? Integer.parseInt(greenMatcher.group(1)) : 0;
    }

    /**
     * @return number of red cubes revealed
     */
    public int getRedCount() {
        return this.redCount;
    }

    /**
     * @return number of blue cubes revealed
     */
    public int getBlueCount() {
        return this.blueCount;
    }

    /**
     * @return number of green cubes revealed
     */
    public int getGreenCount() {
        return this.greenCount;
    }

    /**
     * @return true if this game step would be possible given cube count limits for puzzle 1, false otherwise
     */
    public boolean isPossible() {
        return this.redCount <= this.RED_LIMIT && this.blueCount <= this.BLUE_LIMIT && this.greenCount <= this.GREEN_LIMIT;
    }
}