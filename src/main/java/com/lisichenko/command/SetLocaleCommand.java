package com.lisichenko.command;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocaleCommand implements Command {
    private static final Logger LOG = Logger.getLogger(SetLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        String locale = request.getParameter("localeName");
        if (locale == null) {
            locale = "en";
        }
        HttpSession session = request.getSession();
        session.setAttribute("locale", locale);
        LOG.trace("Set locale for session " + locale);
        LOG.debug("Command finished");
        return request.getHeader("referer");
    }
}
