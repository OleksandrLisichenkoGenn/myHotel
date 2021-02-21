package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Role;
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
public class CreateRawOrderCommandTest {
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
    public void testCreateRawOrderWithSpecificDaysAndRoleIsCustomer() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(1);
        Role role = Role.CUSTOMER;
        when(session.getAttribute("userRole")).thenReturn(role);
        when(request.getParameter("days")).thenReturn("1");
        when(request.getParameter("places")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("1");


        Command command = new CreateRawOrderCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, atLeastOnce())
                .createRawOrder(1, 1, 1, 1);
        assertEquals("/api?command=getOrdersCustomer", actual);
    }

    @Test
    public void testCreateRawOrderWithSpecificDaysAndRoleIsAdmin() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(1);
        Role role = Role.ADMIN;
        when(session.getAttribute("userRole")).thenReturn(role);
        when(request.getParameter("days")).thenReturn("1");
        when(request.getParameter("places")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("1");


        Command command = new CreateRawOrderCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, atLeastOnce())
                .createRawOrder(1, 1, 1, 1);
        assertEquals("/api?command=getOrdersAdmin", actual);
    }

    @Test
    public void testCreateRawOrderWithEmptyFieldDays() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(1);
        Role role = Role.ADMIN;
        when(session.getAttribute("userRole")).thenReturn(role);
        when(request.getParameter("days")).thenReturn("");
        when(request.getParameter("places")).thenReturn("1");
        when(request.getHeader("referer")).thenReturn("index.jsp");

        Command command = new CreateRawOrderCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, never())
                .createRawOrder(anyInt(), anyInt(), anyInt(), anyInt());
        assertEquals("index.jsp?error=1", actual);
    }
}
