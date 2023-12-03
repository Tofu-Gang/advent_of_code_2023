package day03;

import utils.InputFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EngineSchematic {
    // numbers from the schematics; each dict entry is an input row number mapped to a list of numbers from the
    // schematics row
    private final Map<Integer, ArrayList<Number>> numbers = new HashMap<>();
    // any symbol from the schematics which is not a dot or a digit;
    // each dict entry is an input row number mapped to a list of symbols from the schematics row
    private final Map<Integer, ArrayList<Symbol>> symbols = new HashMap<>();

    /**
     * Read the engine schematics and collect all numbers and symbols.
     */
    public EngineSchematic() {
        InputFile inputFile = new InputFile();
        int row = 0;

        while(inputFile.hasNextLine()) {
            this.symbols.put(row, new ArrayList<>());
            this.numbers.put(row, new ArrayList<>());
            String line = inputFile.getNextLine();
            int column = 0;

            while(column < line.length()) {
                char symbol = line.charAt(column);
                if(symbol == '.') {
                    column++;
                } else {
                    if(Character.isDigit(symbol)) {
                        String number = line.substring(column).replaceAll("(\\d+).*", "$1");
                        this.numbers.get(row).add(new Number(row, column, Integer.parseInt(number)));
                        column += number.length();
                    } else {
                        this.symbols.get(row).add(new Symbol(row, column, symbol));
                        column++;
                    }
                }
            }
            row++;
        }
    }

    /**
     * The engine schematic consists of a visual representation of the engine. There are lots of numbers and symbols you
     * don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and
     * should be included in your sum. (Periods (.) do not count as a symbol.)
     *
     * @return What is the sum of all of the part numbers in the engine schematic?
     */
    public int getPartNumbersSum() {
        int sum = 0;

        for(int row: this.numbers.keySet()) {
            for(Number number : this.numbers.get(row)) {
                sum += this.isPartNumber(number) ? number.number() : 0;
            }
        }
        return sum;
    }

    /**
     * A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying
     * those two numbers together.
     *
     * This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out
     * which gear needs to be replaced.
     *
     * @return What is the sum of all of the gear ratios in your engine schematic?
     */
    public int getGearRatiosSum() {
        int sum = 0;

        for(int row: this.symbols.keySet()) {
            for(Symbol symbol: this.symbols.get(row).stream().filter(e -> e.symbol() == '*').toList()) {
                sum += this.gearRatio(symbol);
            }
        }

        return sum;
    }

    /**
     * Any number adjacent to a symbol, even diagonally, is a "part number".
     * @param number the tested number from the engine schematics
     * @return true if the number is a part number, false otherwise
     */
    private boolean isPartNumber(Number number) {
        ArrayList<Symbol> symbolsSubset = new ArrayList<>();
        try {
            symbolsSubset.addAll(this.symbols.get(number.row() - 1));
        } catch (NullPointerException ignored) {

        }
        symbolsSubset.addAll(this.symbols.get(number.row()));
        try {
            symbolsSubset.addAll(this.symbols.get(number.row() + 1));
        } catch (NullPointerException ignored) {

        }

        for(Symbol symbol: symbolsSubset) {
            if(symbol.column() >= number.column() - 1
                    && symbol.column() <= number.column() + String.valueOf(number.number()).length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying
     * those two numbers together.
     * @param symbol a potential gear
     * @return gear ratio if the symbol is indeed a gear, 0 otherwise
     */
    private int gearRatio(Symbol symbol) {
        ArrayList<Number> numbersSubset = new ArrayList<>();
        try {
            numbersSubset.addAll(this.numbers.get(symbol.row() - 1));
        } catch (NullPointerException ignored) {

        }
        numbersSubset.addAll(this.numbers.get(symbol.row()));
        try {
            numbersSubset.addAll(this.numbers.get(symbol.row() + 1));
        } catch (NullPointerException ignored) {

        }

        int adjacentPartNumbersCount = 0;
        int gearRatio = 1;

        for(Number number : numbersSubset) {
            if(number.column() >= symbol.column() - String.valueOf(number.number()).length()
                    && number.column() <= symbol.column() + 1) {
                adjacentPartNumbersCount++;
                gearRatio *= number.number();
            }
        }
        return adjacentPartNumbersCount == 2 ? gearRatio : 0;
    }
}

record Number(int row, int column, int number) {}
record Symbol(int row, int column, char symbol) {}
