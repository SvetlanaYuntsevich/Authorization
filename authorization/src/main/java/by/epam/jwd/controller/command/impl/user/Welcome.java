package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;

public class Welcome implements Command{

	public static final String WELCOME = "welcome";
	private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {

        String welcome = request.getParameter(WELCOME);
        request.setAttribute(WELCOME, welcome);

        return PageContainer.WELCOME;
    }
}
