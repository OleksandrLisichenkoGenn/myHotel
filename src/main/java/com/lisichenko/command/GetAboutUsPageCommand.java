package com.lisichenko.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAboutUsPageCommand implements Command {
    private final static Logger LOG = Logger.getLogger(GetAboutUsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Return about.jsp");
        return "/WEB-INF/jsp/about.jsp";
    }
}
