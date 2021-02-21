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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class CreateOrderWithSpecificRoomCommandTest {
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
    public void testCreateOrderWithSpecificRoomWithNotNullDaysAndRoleIsCustomer() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        Role role = Role.CUSTOMER;
        when(session.getAttribute("userRole")).thenReturn(role);
        when(session.getAttribute("id")).thenReturn(1);
        when(request.getParameter("days")).thenReturn("2");
        when(request.getParameter("idRoom")).thenReturn("1");

        Command command = new CreateOrderWithSpecificRoomCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, times(1))
                .createSpecificRoomOrder(1, 1, 2);
        assertEquals("/api?command=getOrdersCustomer", actual);
    }

    @Test
    public void testCreateOrderWithSpecificRoomWithNotNullDaysAndRoleIsAdmin() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        Role role = Role.ADMIN;
        when(session.getAttribute("userRole")).thenReturn(role);
        when(session.getAttribute("id")).thenReturn(1);
        when(request.getParameter("days")).thenReturn("2");
        when(request.getParameter("idRoom")).thenReturn("1");
        when(request.getParameter("price")).thenReturn("100");

        Command command = new CreateOrderWithSpecificRoomCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, times(1))
                .createSpecificRoomOrder(1, 1, 2);
        assertEquals("/api?command=getOrdersAdmin", actual);
    }

    @Test
    public void testCreateOrderWithSpecificRoomWithEmptyFieldDays() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        Role role = Role.CUSTOMER;
        when(session.getAttribute("userRole")).thenReturn(role);
        when(session.getAttribute("id")).thenReturn(1);
        when(request.getParameter("days")).thenReturn("");
        when(request.getHeader("referer")).thenReturn("index.jsp");

        Command command = new CreateOrderWithSpecificRoomCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, never())
                .createSpecificRoomOrder(anyInt(), anyInt(), anyInt());
        assertEquals("index.jsp&error=1", actual);
    }
}
