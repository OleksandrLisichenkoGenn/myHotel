package com.lisichenko.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetRegisterPageCommandTest {
    @Test
    public void testReturnedURIRegisterPage() {
        Command command = new GetRegisterPageCommand();
        String actual = command.execute(null, null);
        assertEquals("/WEB-INF/jsp/register.jsp", actual);
    }
}