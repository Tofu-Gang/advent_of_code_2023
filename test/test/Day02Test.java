package test;

import day02.Day02;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void puzzle1() {
        assertEquals(Day02.puzzle1(), 2237);
    }

    @Test
    public void puzzle2() {
        assertEquals(Day02.puzzle2(), 66681);
    }
}