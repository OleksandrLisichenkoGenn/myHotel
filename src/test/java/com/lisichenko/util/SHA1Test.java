package com.lisichenko.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SHA1Test {
    @Test
    public void testHashingString() {
        String expected = "356a192b7913b04c54574d18c28d46e6395428ab";
        String actual = SHA1.sha1("1");
        assertEquals(expected, actual);
    }
}