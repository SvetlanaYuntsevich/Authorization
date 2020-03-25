package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class SignOut implements Command {
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		LOGGER.debug("start SignOut Command");

		request.getSession().invalidate();
		LOGGER.debug("finish SignOut Command");

		return PageContainer.INDEX_PAGE;

	}

}
