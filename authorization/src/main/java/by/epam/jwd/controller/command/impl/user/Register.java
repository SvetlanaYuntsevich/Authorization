package by.epam.jwd.controller.command.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.jwd.controller.PageContainer;
import by.epam.jwd.controller.command.Command;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;
import by.epam.jwd.service.validator.Validator;

public class Register implements Command {
    @Override
    public String execute(HttpServletRequest request){
    	User user = null;
        String page;
        if (validate(request)) {
            Validator validator = Validator.getInstance();

            try {
                if (validator.validateUniqueLogin(request.getParameter("login"))) {

                    user = new User();
                    user.setName(request.getParameter("name"));
                    user.setSurname(request.getParameter("surname"));
                    user.setEmail(request.getParameter("email"));
                    user.setLogin(request.getParameter("login"));
                    user.setPassword(request.getParameter("password"));

                    ServiceFactory factory = ServiceFactory.getInstance();
                    UserService userService = factory.getUserService();

                        userService.register(user);
                        if (user != null) {
                            request.setAttribute("user", user);
                            request.setAttribute("name", user.getName());
                            request.getSession().setAttribute("user", user);
                        }
                    page = PageContainer.CONTINUE_REGISTER;
                } else {
                    request.setAttribute("error", "Login is not unique. Please try again.");
                    page = PageContainer.ERROR_PAGE;
                }
               
            } catch (ServiceException e) {
                page = PageContainer.ERROR_PAGE;
            } 
        } else {
            request.setAttribute("error", "The data entered is not correct. Please try again.");
            page = PageContainer.ERROR_PAGE;
        }
        return page;
    }


    private boolean validate(HttpServletRequest request) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Validator validator = Validator.getInstance();

        boolean result = validator.validateNameOrSurname(name) &&
                validator.validateNameOrSurname(surname) &&
                validator.validateEmail(email) &&
                validator.validateLoginOrPassword(login) &&
                validator.validateLoginOrPassword(password);
        return result;
    }
}
