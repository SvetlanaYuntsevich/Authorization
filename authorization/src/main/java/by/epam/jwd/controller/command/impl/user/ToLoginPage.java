package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class ToLoginPage implements Command {

    public static final String TO_LOGIN = "toLogin";
	private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public String execute(HttpServletRequest request) {
		LOGGER.debug("start ToLoginPage Command");

        String toLogin = request.getParameter(TO_LOGIN);
        request.setAttribute(TO_LOGIN, toLogin);
		LOGGER.debug("finish ToLoginPage Command");

        return PageContainer.LOGIN_PAGE;
    }
}