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
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class PayOrderCommandTest {
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
    public void testPayOrderByClient() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("orderId")).thenReturn("1");



        Command command = new PayOrderCommand();

        String actual = command.execute(request, response);

        verify(dbManagerMock, times(1))
                .payOrder(1);
        assertEquals("/api?command=getOrdersCustomer", actual);
    }
}