package com.lisichenko.filter;

import com.lisichenko.entities.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class AccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AccessFilter.class);
    private static final String SPACE = " ";
    private final Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.debug("Filter initialization starts");
        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin").split(SPACE)));
        accessMap.put(Role.CUSTOMER, asList(filterConfig.getInitParameter("customer").split(SPACE)));
        LOG.trace("Access map --> " + accessMap);

        commons = asList(filterConfig.getInitParameter("common").split(SPACE));
        LOG.trace("Common commands --> " + commons);

        outOfControl = asList(filterConfig.getInitParameter("out-of-control").split(SPACE));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");

        if (accessAllowed(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            String errorPath = "/WEB-INF/jsp/error.jsp";
            request.getRequestDispatcher(errorPath).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }
}
