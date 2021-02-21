package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateOrderWithSpecificRoomCommand implements Command {
    private final static Logger LOG = Logger.getLogger(CreateOrderWithSpecificRoomCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("userRole");
        LOG.trace("Get session from request");
        int id = (int) session.getAttribute("id");
        String days = request.getParameter("days");
        if (days.isEmpty()) {
            return request.getHeader("referer") + "&error=1";
        }
        int idRoom = Integer.parseInt(request.getParameter("idRoom"));
        DBManager dbManager = DBManager.getInstance();
        dbManager.createSpecificRoomOrder(id, idRoom, Integer.parseInt(days));
        LOG.trace("Create order with specific room");

        if (role.equals(Role.CUSTOMER)) {
            LOG.debug("Return customer page. Command finished");
            return "/api?command=getOrdersCustomer";
        } else {
            LOG.debug("Return admins page. Command finished");
            return "/api?command=getOrdersAdmin";
        }
    }
}
