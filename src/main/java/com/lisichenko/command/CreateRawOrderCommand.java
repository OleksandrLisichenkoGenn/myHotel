package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateRawOrderCommand implements Command {
    private static final Logger LOG = Logger.getLogger(CreateRawOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        LOG.trace("Get id account from request " + id);
        Role role = (Role) session.getAttribute("userRole");
        String days = request.getParameter("days");
        String places = request.getParameter("places");

        if (days.isEmpty() || places.isEmpty()) {
            return request.getHeader("referer") + "?error=1";
        }

        int type = Integer.parseInt(request.getParameter("type"));
        DBManager dbManager = DBManager.getInstance();
        dbManager.createRawOrder(id, Integer.parseInt(days), Integer.parseInt(places), type);
        LOG.trace("Create raw order with values which left client");
        if (role.equals(Role.CUSTOMER)) {
            LOG.debug("Return customer page. Command finished");
            return "/api?command=getOrdersCustomer";
        } else {
            LOG.debug("Return admins page. Command finished");
            return "/api?command=getOrdersAdmin";
        }
    }
}
