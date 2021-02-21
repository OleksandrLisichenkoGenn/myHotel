package com.lisichenko.command;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogoutCommandTest {
    @Test
    public void logoutTest() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        Command command = new LogoutCommand();
        when(request.getSession(false)).thenReturn(session);

        String actual = command.execute(request, response);


        verify(session, atLeastOnce()).invalidate();
        assertEquals("index.jsp", actual);
    }
}