package com.lisichenko;

import com.lisichenko.command.Command;
import com.lisichenko.command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front Servlet is implementation Front Controller Pattern.
 * WebMapping is /api
 */
@WebServlet("/api")
public class FrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        Command command = CommandContainer.getInstance().getCommand(commandName);

        String view = command.execute(request, response);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter("command");

        Command command = CommandContainer.getInstance().getCommand(commandName);

        String view = command.execute(request, response);

        response.sendRedirect(view);
    }
}
