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
	private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String SESSION_ROLE = "role";
    private static final String SESSION_USER = "user";
    private static final String SESSION_USER_ID = "id";
    private static final String SESSION_USER_NAME = "name";
    private static final String SESSION_USER_SURNAME = "surname";
   // private static final String SESSION_USER_EMAIL = "email";
    private static final String SESSION_USER_LOGIN = "login";



    @Override
    public String execute(HttpServletRequest  request) {
        LOGGER.debug("start SignInCommand");

    	String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String page = null;	

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession(true);
        User user = null;
        try {
            user = userService.signIn(login, password);

            if (user != null) {
                request.setAttribute(SESSION_USER, user);
                session.setAttribute(SESSION_USER, user);
                session.setAttribute(SESSION_USER_ID, user.getId());
                session.setAttribute(SESSION_USER_NAME, user.getName());
                session.setAttribute(SESSION_USER_SURNAME, user.getSurname());
              //  session.setAttribute(SESSION_USER_EMAIL, user.getEmail());
                session.setAttribute(SESSION_USER_LOGIN, user.getLogin());
                session.setAttribute(SESSION_ROLE, user.getRole());
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