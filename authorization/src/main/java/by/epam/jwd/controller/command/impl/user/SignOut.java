package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class SignOut implements Command {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String SESSION_ROLE = "role";
	private static final String SESSION_USER_ID = "id";
	private static final String SESSION_USER_LOGIN = "login";

	@Override
	public String execute(HttpServletRequest request) {
		LOGGER.debug("start SignOut Command");

		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_USER_ID);
		session.removeAttribute(SESSION_USER_LOGIN);
		session.removeAttribute(SESSION_ROLE);
		LOGGER.debug("finish SignOut Command");

		return PageContainer.INDEX_PAGE;

	}

}
