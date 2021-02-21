package com.lisichenko.command;


import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SetLocaleCommandTest {
    @Test
    public void testSetLocaleWhenLocaleNotNull() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("localeName")).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        when(request.getHeader("referer")).thenReturn("index.jsp");
        String locale = "en";
        Command command = new SetLocaleCommand();

        String actual = command.execute(request, response);

        verify(session, atLeastOnce()).setAttribute("locale", locale);
        assertEquals("index.jsp", actual);
    }
    @Test
    public void testSetLocaleWhenLocaleIsNull() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("localeName")).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(request.getHeader("referer")).thenReturn("index.jsp");
        String locale = "en";
        Command command = new SetLocaleCommand();

        String actual = command.execute(request, response);

        verify(session, atLeastOnce()).setAttribute("locale", locale);

        assertEquals("index.jsp", actual);
    }
}