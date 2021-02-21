package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Room;
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
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class GetRoomsCommandTest {
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
    public void testGetRoomsWithSortIsNullPageParamNotNull() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("sort")).thenReturn(null);
        when(request.getParameter("page")).thenReturn("1");
        List<Room> roomList = new ArrayList<>();
        when(dbManagerMock.getAllRooms(4,4,"status")).thenReturn(roomList);
        when(dbManagerMock.getCountOfPages()).thenReturn(1);
        List<Integer> pagesList = new ArrayList<>();
        pagesList.add(1);


        Command command = new GetRoomsCommand();

        String actual = command.execute(request, response);
        verify(request,atLeastOnce()).setAttribute("pages", pagesList);;
        verify(request, atLeastOnce()).setAttribute("roomList", roomList);
        assertEquals("/WEB-INF/jsp/rooms.jsp", actual);
    }

    @Test
    public void testGetRoomsWithSortIsNotNull() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("sort")).thenReturn("");
        when(request.getParameter("page")).thenReturn("1");
        List<Room> roomList = new ArrayList<>();
        when(dbManagerMock.getAllRooms(4,4,"status")).thenReturn(roomList);
        when(dbManagerMock.getCountOfPages()).thenReturn(1);
        List<Integer> pagesList = new ArrayList<>();
        pagesList.add(1);

        Command command = new GetRoomsCommand();

        String actual = command.execute(request, response);
        verify(request,atLeastOnce()).setAttribute("pages", pagesList);;
        verify(request, atLeastOnce()).setAttribute("roomList", roomList);
        assertEquals("/WEB-INF/jsp/rooms.jsp", actual);
    }

    @Test
    public void testGetRoomsWithSortIsPrice() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("sort")).thenReturn("byPrice");
        when(request.getParameter("page")).thenReturn("1");
        List<Room> roomList = new ArrayList<>();
        when(dbManagerMock.getAllRooms(4,4,"price")).thenReturn(roomList);
        when(dbManagerMock.getCountOfPages()).thenReturn(1);
        List<Integer> pagesList = new ArrayList<>();
        pagesList.add(1);

        Command command = new GetRoomsCommand();

        String actual = command.execute(request, response);
        verify(request,atLeastOnce()).setAttribute("pages", pagesList);;
        verify(request, atLeastOnce()).setAttribute("roomList", roomList);
        assertEquals("/WEB-INF/jsp/rooms.jsp", actual);
    }

    @Test
    public void testGetRoomsWithSortIsPlaces() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("sort")).thenReturn("byPlaces");
        when(request.getParameter("page")).thenReturn("1");
        List<Room> roomList = new ArrayList<>();
        when(dbManagerMock.getAllRooms(4,4,"places")).thenReturn(roomList);
        when(dbManagerMock.getCountOfPages()).thenReturn(1);
        List<Integer> pagesList = new ArrayList<>();
        pagesList.add(1);

        Command command = new GetRoomsCommand();

        String actual = command.execute(request, response);
        verify(request,atLeastOnce()).setAttribute("pages", pagesList);;
        verify(request, atLeastOnce()).setAttribute("roomList", roomList);
        assertEquals("/WEB-INF/jsp/rooms.jsp", actual);
    }

    @Test
    public void testGetRoomsWithSortIsType() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("sort")).thenReturn("byType");
        when(request.getParameter("page")).thenReturn("1");
        List<Room> roomList = new ArrayList<>();
        when(dbManagerMock.getAllRooms(4,4,"type")).thenReturn(roomList);
        when(dbManagerMock.getCountOfPages()).thenReturn(1);
        List<Integer> pagesList = new ArrayList<>();
        pagesList.add(1);

        Command command = new GetRoomsCommand();

        String actual = command.execute(request, response);
        verify(request,atLeastOnce()).setAttribute("pages", pagesList);;
        verify(request, atLeastOnce()).setAttribute("roomList", roomList);
        assertEquals("/WEB-INF/jsp/rooms.jsp", actual);
    }
}