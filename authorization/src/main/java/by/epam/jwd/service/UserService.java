package by.epam.jwd.service;

import java.util.List;

import by.epam.jwd.entity.User;
import by.epam.jwd.service.exception.ServiceException;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;

	void register(User user) throws ServiceException;

	List<User> findAll() throws ServiceException;

	public void setDiscountById(int id, int discount) throws ServiceException;

	public void update(User user) throws ServiceException;

	public User getUserById(int id) throws ServiceException;

	public void setRoleById(int id, String role) throws ServiceException;

}
