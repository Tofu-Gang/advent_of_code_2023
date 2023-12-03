package test;

import day03.Day03;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day03Test {

    @Test
    public void puzzle1() {
        assertEquals(Day03.puzzle1(), 553825);
    }

    @Test
    public void puzzle2() {
        assertEquals(Day03.puzzle2(), 93994191);
    }
}