package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class CreateAccountCommandTest {
    private final DBManager dbManagerMock = mock(DBManager.class);

    @Before
    public void setUp() {
        when(DBManager.getInstance()).thenReturn(dbManagerMock);
    }

    @BeforeClass
    public static void beforeClass() {
        mockStatic(DBManager.class);
    }

    @Test
    public void testCreateUser() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("email")).thenReturn("email@mail.com");
        when(request.getParameter("password")).thenReturn("password");
        HttpServletResponse response = mock(HttpServletResponse.class);

        Command command = new CreateAccountCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, times(1))
                .createAccount(eq("name"),
                        eq("email@mail.com"),
                        eq("5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"));
        assertEquals("index.jsp", actual);
    }

    @Test
    public void testCreateUserWithInvalidEmail() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getHeader("referer")).thenReturn("error.jsp");
        HttpServletResponse response = mock(HttpServletResponse.class);
        Command command = new CreateAccountCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, never()).createAccount(anyString(), anyString(), anyString());
        assertEquals("error.jsp&error=2", actual);
    }

    @Test
    public void testCreateUserWithEmptyPassword() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("email")).thenReturn("email@email.com");
        when(request.getParameter("password")).thenReturn("");
        when(request.getHeader("referer")).thenReturn("error.jsp");
        HttpServletResponse response = mock(HttpServletResponse.class);
        Command command = new CreateAccountCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, never()).createAccount(anyString(), anyString(), anyString());
        assertEquals("error.jsp&error=2", actual);
    }

    @Test
    public void testCreateUserWithEmptyName() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("email")).thenReturn("email@email.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getHeader("referer")).thenReturn("error.jsp");
        HttpServletResponse response = mock(HttpServletResponse.class);
        Command command = new CreateAccountCommand();

        String actual = command.execute(request, response);
        verify(dbManagerMock, never()).createAccount(anyString(), anyString(), anyString());

        assertEquals("error.jsp&error=2", actual);
    }
}
