package com.lisichenko.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetAboutUsPageCommandTest {
    @Test
    public void testReturnedURI() {
        Command command = new GetAboutUsPageCommand();
        String actual = command.execute(null,null);
        assertEquals("/WEB-INF/jsp/about.jsp", actual);
    }
}