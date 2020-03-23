package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class ToLoginPage implements Command {

    public static final String TO_LOGIN = "toLogin";

    @Override
    public String execute(HttpServletRequest request) {

        String toLogin = request.getParameter(TO_LOGIN);
        request.setAttribute(TO_LOGIN, toLogin);

        return PageContainer.LOGIN_PAGE;
    }
}