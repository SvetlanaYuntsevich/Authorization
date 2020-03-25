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

public class ContinueRegister implements Command {
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		LOGGER.debug("start ContinueRegister command");

		String page;

		HttpSession session = request.getSession(true);
		User newUser = (User) request.getSession().getAttribute("user");
		LOGGER.debug("ContinueRegister new User: " + newUser);

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		User user = null;

		try {
			user = userService.signIn(newUser.getLogin(), newUser.getPassword());
			LOGGER.debug("ContinueRegister User: " + user);
			session.setAttribute("user", user);
			session.setAttribute("id", user.getId());
			session.setAttribute("name", user.getName());
			session.setAttribute("surname", user.getSurname());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("role", user.getRole());
			page = PageContainer.WELCOME;

		} catch (ServiceException e) {
			LOGGER.error("ContinueRegister error.", e);
			page = PageContainer.ERROR_PAGE;
		}
		LOGGER.debug("finish ContinueRegister: " + user);
		return page;
	}

}
