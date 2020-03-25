package by.epam.jwd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jwd.controller.command.Command;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -2048135898722344755L;

	private static final Logger LOGGER = LogManager.getLogger();

	public static final String COMMAND = "command";

	private final CommandProvider provider = new CommandProvider();

	public Controller() {
	}

	public void init() {
		LOGGER.debug("servlet init");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug("start get method");
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug("start post method");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);

		Command command = provider.getCommand(commandName);
		LOGGER.debug("command: " + command.getClass().getSimpleName());

		String page = command.execute(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		try {
			dispatcher.forward(request, response);

		} catch (ServletException | IOException e) {
			LOGGER.error("Controller error.", e);
			page = PageContainer.ERROR_PAGE;
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

	public void destroy() {
		LOGGER.debug("servlet destroy");
		super.destroy();
	}
}
