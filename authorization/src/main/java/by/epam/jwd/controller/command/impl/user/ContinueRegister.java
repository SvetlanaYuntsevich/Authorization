package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class ContinueRegister implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String page;

		// HttpSession session = request.getSession(true);
		User newUser = (User) request.getSession().getAttribute("user");

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		User user = null;

		try {
			user = userService.signIn(newUser.getLogin(), newUser.getPassword());
			user.setName(request.getParameter("name"));
			user.setSurname(request.getParameter("surname"));
			user.setEmail(request.getParameter("email"));
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			page = PageContainer.LOGIN_PAGE;

		} catch (ServiceException e) {
			page = PageContainer.ERROR_PAGE;
		}
		return page;
	}

}
