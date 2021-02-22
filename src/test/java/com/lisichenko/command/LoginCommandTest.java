package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Account;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class LoginCommandTest {
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
    public void testLoginWithValidAccount() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("email")).thenReturn("admin@db.com");
        when(request.getParameter("password")).thenReturn("1");
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        account.setEmail("admin@db.com");
        account.setPassword("356a192b7913b04c54574d18c28d46e6395428ab");
        account.setRoleId(1);
        when(dbManagerMock.getAccount("admin@db.com")).thenReturn(account);
        Command command = new LoginCommand();

        String actual = command.execute(request, response);

        verify(request, times(1)).getSession();
        assertEquals("/index.jsp", actual);
    }

    @Test
    public void testLoginWithAccountWhichEqualsNull() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        String empty = "";
        when(request.getParameter("email")).thenReturn(empty);
        when(request.getParameter("password")).thenReturn(empty);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(dbManagerMock.getAccount(empty)).thenReturn(null);
        Command command = new LoginCommand();

        String actual = command.execute(request, response);

        assertEquals("index.jsp?error=4", actual);
    }
}
