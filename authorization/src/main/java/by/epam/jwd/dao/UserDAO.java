package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.entity.User;

public interface UserDAO {

	User signIn(String login, String password) throws DAOException;

	void register(User user) throws DAOException;

	List<User> getAllUsers() throws DAOException;
	
	public void setDiscountById(int id, int discount) throws DAOException;
	
	public void update(User user) throws DAOException;
	
	public User getUserById(int id) throws DAOException;
	
	public void setRoleById(int id, String role) throws DAOException;

}