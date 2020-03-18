package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.DAOFactory;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;

public class UserServiceImpl implements UserService{
	
	 private DAOFactory daoFactory = DAOFactory.getInstance();
	 private UserDAO userDAO = daoFactory.getUserDAO();
	 
	 
	@Override
	public User signIn(String login, String password) throws ServiceException {
		if (login != null && password != null) {
            try {
                User user = userDAO.signIn(login, password);
                return user;
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Incorrect parameters.");
        }
	}

	@Override
	public void register(User user) throws ServiceException {
		if (user == null) {
            throw new ServiceException("Please create the user!");
        }
        try {
            userDAO.register(user);
        } catch (DAOException e) {
            throw new ServiceException("Registration fail", e);
        }
		
	}
	@Override
	public List<User> findAll() throws ServiceException {
		 try {
	            List<User> users = userDAO.findAll();
	            return users;
	        } catch (DAOException e) {
	            throw new ServiceException(e);
	        }
	}

}
