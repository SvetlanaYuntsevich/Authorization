package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class Welcome implements Command{

	public static final String WELCOME = "toLogin";

    @Override
    public String execute(HttpServletRequest request) {

        String toLogin = request.getParameter(WELCOME);
        request.setAttribute(WELCOME, toLogin);

        return PageContainer.WELCOME;
    }
}
