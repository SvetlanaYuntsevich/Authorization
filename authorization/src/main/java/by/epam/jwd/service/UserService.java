package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.entity.User;
import by.epam.jwd.service.exception.ServiceException;

public interface UserService {
	    User signIn(String login, String password)throws ServiceException;
	    
	    void register(User user)throws ServiceException;
	    
	    List<User> findAll () throws ServiceException;

}
