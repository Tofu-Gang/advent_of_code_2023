package test;

import day01.Day01;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {

    @Test
    public void puzzle1() {
        assertEquals(Day01.puzzle1(), 55123);
    }

    @Test
    public void puzzle2() {
        assertEquals(Day01.puzzle2(), 55260);
    }
}