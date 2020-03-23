package by.epam.jwd.service.impl;

import java.util.List;

import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.dao.factory.DAOFactory;
import by.epam.jwd.entity.User;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private DAOFactory daoFactory = DAOFactory.getInstance();
	private UserDAO userDAO = daoFactory.getUserDAO();

	@Override
	public User signIn(String login, String password) throws ServiceException {
		if (login != null && login != "" && password != null && password != "") {
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
			List<User> users = userDAO.getAllUsers();
			return users;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void setDiscountById(int id, int discount) throws ServiceException {
		if (id > 0 && discount >= 0 && discount <= 100) {
			try {
				userDAO.setDiscountById(id, discount);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Incorrect parameters.");
		}
	}

	@Override
	public void update(User user) throws ServiceException {
		if (user == null) {
			throw new ServiceException("Please create the user!");
		}
		try {
			userDAO.update(user);
		} catch (DAOException e) {
			throw new ServiceException("update user fail", e);
		}
	}

	@Override
	public User getUserById(int id) throws ServiceException {
		if (id > 0) {
			try {
				User user = userDAO.getUserById(id);
				return user;
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Incorrect user id.");
		}
	}

	@Override
	public void setRoleById(int id, String role) throws ServiceException {
		if (id > 0 && role != null) {
			try {
				userDAO.setRoleById(id, role);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Incorrect parameters.");
		}
	}

}
