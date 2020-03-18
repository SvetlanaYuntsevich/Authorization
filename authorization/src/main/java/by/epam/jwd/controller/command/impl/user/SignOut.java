package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class SignOut implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		 request.getSession().invalidate();
	        return PageContainer.INDEX_PAGE;
	}

}
