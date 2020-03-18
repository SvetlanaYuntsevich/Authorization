package by.epam.jwd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jwd.controller.command.Command;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -2048135898722344755L;

	public static final String COMMAND = "command";

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        
        Command command = provider.getCommand(commandName);

        String page = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        try {
            dispatcher.forward(request,response);

        } catch (ServletException | IOException e) {
            page = PageContainer.ERROR_PAGE;
            dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    public void destroy() {
        super.destroy();
    }
}
