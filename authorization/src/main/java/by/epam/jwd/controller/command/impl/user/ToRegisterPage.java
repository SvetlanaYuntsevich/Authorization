package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class ToRegisterPage implements Command {

    public static final String TO_REGISTER = "toRegister";

    @Override
    public String execute(HttpServletRequest request) {

        String toRegister = request.getParameter(TO_REGISTER);
        request.setAttribute(TO_REGISTER, toRegister);

        return PageContainer.REGISTER_PAGE;
    }
}
