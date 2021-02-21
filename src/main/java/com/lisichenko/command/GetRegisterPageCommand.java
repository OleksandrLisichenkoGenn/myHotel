package com.lisichenko.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRegisterPageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetRegisterPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start. Return register.jsp.");
        LOG.debug("Command finished");
        return "/WEB-INF/jsp/register.jsp";
    }
}
