package com.lisichenko.command;

import com.lisichenko.db.DBManager;
import com.lisichenko.entities.Account;
import com.lisichenko.entities.Role;
import com.lisichenko.util.SHA1;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command start");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashPassword = SHA1.sha1(password);
        DBManager dbManager = DBManager.getInstance();
        Account account = dbManager.getAccount(email);
        LOG.trace("Get account by email from data base " + account);

        if (account == null || !hashPassword.equals(account.getPassword())) {
            LOG.error("Invalid email or password");
            return "index.jsp?error=4";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("name", account.getName());
            session.setAttribute("createTime", account.getCreateTime());
            session.setAttribute("email", account.getEmail());
            session.setAttribute("id", account.getId());
            session.setAttribute("userRole", Role.getRole(account));
            LOG.trace("Set account in session. Login was successfully");
            LOG.debug("Command finished");
        }
        return "/index.jsp";
    }
}
