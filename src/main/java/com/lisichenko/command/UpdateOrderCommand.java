package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderCommand implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int orderRoomId = Integer.parseInt(request.getParameter("orderRoomId"));
        DBManager dbManager = DBManager.getInstance();
        if (orderRoomId != 0) {
            LOG.trace("Update order with specific room");
            dbManager.updateOrderByAdmin(orderId);
        } else {
            int roomId = Integer.parseInt(request.getParameter("room"));
            dbManager.updateRawOrder(orderId, roomId);
            LOG.trace("Update order without room");
        }
        LOG.debug("Command finished");
        return "/api?command=getOrdersAdmin";
    }
}
