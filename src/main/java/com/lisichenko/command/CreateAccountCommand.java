package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.util.SHA1;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountCommand implements Command {
    private final static Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    private final static String REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        String email = request.getParameter("email");
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        if (password.isEmpty() || name.isEmpty() || !matcher.find()) {
            return request.getHeader("referer") + "&error=2";
        }
        String hashingPassword = SHA1.sha1(password);
        LOG.trace("Get name, email, password parameters from request");

        DBManager dbManager = DBManager.getInstance();
        dbManager.createAccount(name, email, hashingPassword);
        LOG.debug("Create new account. Command finished");
        return "index.jsp";
    }
}
