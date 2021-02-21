package com.lisichenko.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandContainerTest {
    @Test
    public void testGetSetLocaleCommand() {
        CommandContainer commandContainer = CommandContainer.getInstance();

        Command actual = commandContainer.getCommand("setLocale");
        assertTrue(actual instanceof SetLocaleCommand);
    }
}