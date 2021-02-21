package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Order;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class GetOrdersForCustomerCommandTest {
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
    public void testGetOrdersForCustomer() {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(1);
        List<Order> list = new ArrayList<>();
        when(dbManagerMock.getOrdersForCustomer(1)).thenReturn(list);

        Command command = new GetOrdersForCustomerCommand();

        String actual = command.execute(request, response);

        verify(request, atLeastOnce()).setAttribute("list", list);
        assertEquals("/WEB-INF/jsp/customer_account.jsp", actual);
    }
}