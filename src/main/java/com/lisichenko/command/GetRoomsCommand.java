package com.lisichenko.command;


import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Room;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetRoomsCommand implements Command {
    private static final String SORT_BY_STATUS = "room_status_id";
    private static final String SORT_BY_TYPE = "room_type_id";
    private static final String SORT_BY_PLACES = "places";
    private static final String SORT_BY_PRICE = "price";
    private static final Logger LOG = Logger.getLogger(GetRoomsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");

        String requestParameter = request.getParameter("sort");
        String sort = getSortOrderBy(requestParameter);

        request.setAttribute("sort", requestParameter);
        LOG.trace("Set sorting for rooms " + sort);
        String pageParameter = request.getParameter("page");
        int page = 0;

        if (pageParameter != null) {
            page = Integer.parseInt(pageParameter);
        }

        int limit = 4;
        int offset = limit * page;
        DBManager dbManager = DBManager.getInstance();
        List<Room> roomList = dbManager.getAllRooms(limit, offset, sort);
        request.setAttribute("roomList", roomList);
        LOG.trace("Get and set rooms for pagination " + roomList);
        int counterRooms = dbManager.getCountOfPages();
        int pages = (int) Math.ceil((double) counterRooms / limit);
        List<Integer> pagesList = new ArrayList<>();

        for (int i = 1; i <= pages; i++) {
            pagesList.add(i);
        }

        request.setAttribute("pages", pagesList);
        LOG.trace("Set page list for one of page " + pagesList);
        LOG.debug("Command finished");
        return "/WEB-INF/jsp/rooms.jsp";
    }

    private String getSortOrderBy(String sort) {
        String result = sort;

        if (result == null) {
            return SORT_BY_STATUS;
        }

        switch (sort) {
            case ("byPrice"):
                result = SORT_BY_PRICE;
                break;
            case ("byPlaces"):
                result = SORT_BY_PLACES;
                break;
            case ("byType"):
                result = SORT_BY_TYPE;
                break;
            default:
                result = SORT_BY_STATUS;
        }
        return result;
    }
}
