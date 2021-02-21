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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class GetOrdersForAdminCommandTest {
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
    public void testGetOrdersForAdminWithRoomsForRawOrder() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<Order> ordersForAdmin = new ArrayList<>();
        Order order = new Order();
        order.setId(1);
        order.setPlaces(1);
        order.setType("ECONOMY");
        order.setRoomId(0);
        ordersForAdmin.add(order);
        when(dbManagerMock.getOrdersForAdmin()).thenReturn(ordersForAdmin);
        doNothing().when(request).setAttribute("ordersForAdmin", ordersForAdmin);
        Command command = new GetOrdersForAdminCommand();


        String actual = command.execute(request, response);


        verify(dbManagerMock, atLeastOnce()).getOrdersForAdmin();
        verify(dbManagerMock, atLeastOnce()).getAllRooms(1,"ECONOMY");
        verify(request, atLeastOnce()).setAttribute(anyString(), anyList());
        assertEquals("/WEB-INF/jsp/admin_account.jsp", actual);
    }
}