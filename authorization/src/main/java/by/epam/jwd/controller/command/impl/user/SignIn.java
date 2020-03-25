package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class SignIn implements Command {
	private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest  request) {
        LOGGER.debug("start SignInCommand");

    	String login = request.getParameter("login");
        String password = request.getParameter("password");
        String page = null;	

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession(true);
        User user = null;
        try {
            user = userService.signIn(login, password);

            if (user != null) {
                request.setAttribute("user", user);
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("surname", user.getSurname());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("login", user.getLogin());
                session.setAttribute("role", user.getRole());
                page = PageContainer.WELCOME;
                
            } else {
                session.setAttribute("error", "User not found. Create your account, please.");
                page = PageContainer.ERROR_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.error("SignInCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish SignInCommand: " + user);
        return page;
    }
}