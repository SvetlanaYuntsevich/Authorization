package by.epam.jwd.service.factory;

import by.epam.jwd.service.UserService;
import by.epam.jwd.service.impl.UserServiceImpl;

public class ServiceFactory {
	 private static final ServiceFactory INSTANCE = new ServiceFactory();

	    private static final UserService USER_SERVICE_INSTANCE = new UserServiceImpl();

	    private ServiceFactory() {}

	    public static ServiceFactory getInstance() {
	        return INSTANCE;
	    }

	    public UserService getUserService() {
	        return USER_SERVICE_INSTANCE;
	    }

}
