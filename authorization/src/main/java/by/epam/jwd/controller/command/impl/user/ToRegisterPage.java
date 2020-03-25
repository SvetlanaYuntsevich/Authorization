package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class ToRegisterPage implements Command {

    public static final String TO_REGISTER = "toRegister";
	private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
		LOGGER.debug("start ToRegisterPage Command");

        String toRegister = request.getParameter(TO_REGISTER);
        request.setAttribute(TO_REGISTER, toRegister);
		LOGGER.debug("finish ToRegisterPage Command");

        return PageContainer.REGISTER_PAGE;
    }
}
