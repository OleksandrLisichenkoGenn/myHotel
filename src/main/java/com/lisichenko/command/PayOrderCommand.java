package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayOrderCommand implements Command {
    private static final Logger LOG = Logger.getLogger(PayOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        DBManager dbManager = DBManager.getInstance();
        dbManager.payOrder(orderId);
        LOG.debug("Command finished");
        return "/api?command=getOrdersCustomer";
    }
}
