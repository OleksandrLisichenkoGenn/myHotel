package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetOrdersForCustomerCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetOrdersForCustomerCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        HttpSession session = request.getSession();
        int accountId = (int) session.getAttribute("id");
        LOG.trace("Get session from request");
        DBManager dbManager = DBManager.getInstance();
        List<Order> orders = dbManager.getOrdersForCustomer(accountId);
        request.setAttribute("list", orders);
        LOG.debug("Set List of orders for customer" + orders + ". Command finished");
        return "/WEB-INF/jsp/customer_account.jsp";
    }
}
