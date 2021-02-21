package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Order;
import com.lisichenko.entities.Room;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOrdersForAdminCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetOrdersForAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        DBManager dbManager = DBManager.getInstance();
        List<Order> ordersForAdmin = dbManager.getOrdersForAdmin();
        request.setAttribute("ordersForAdmin", ordersForAdmin);
        LOG.trace("Set list all of orders for admins " + ordersForAdmin);
        Map<String, List<Room>> orderIdRoomsMap = new HashMap<>();

        List<Order> rawOrders = new ArrayList<>();
        for (Order order : ordersForAdmin) {
            if (order.getRoomId() == 0) {
                rawOrders.add(order);
                LOG.trace("Add raw order in the HashMap");
            }
        }
        for (Order order : rawOrders) {
            int id = order.getId();
            int places = order.getPlaces();
            String type = order.getType();
            List<Room> listForRaw = dbManager.getAllRooms(places, type);
            if (listForRaw.isEmpty()) {
                String errorMessageOrder = "Sorry! Have no rooms for this order!";
                request.setAttribute("errorMessage", errorMessageOrder);
            }
            LOG.trace("Get rooms for raw order with same places and type what wish client " + listForRaw);
            orderIdRoomsMap.put(String.valueOf(id), listForRaw);
        }
        request.setAttribute("map", orderIdRoomsMap);
        LOG.trace("Set map raws orders for choosing by admin" + orderIdRoomsMap);
        LOG.debug("Command finished");
        return "/WEB-INF/jsp/admin_account.jsp";
    }
}
