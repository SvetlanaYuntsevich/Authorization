package by.epam.jwd.dao.factory;

import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private static final UserDAO USER_DAO_INSTANCE = new UserDAOImpl();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return INSTANCE;
	}
	
	public UserDAO getUserDAO() {
		return USER_DAO_INSTANCE;
	}
	


}
