package by.epam.jwd.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.connection.ConnectionPool;
import by.epam.jwd.connection.ConnectionPoolException;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.User;



public class UserDAOImpl implements UserDAO {

   private static final String SQL_INSERT_USER
            = "INSERT INTO users (surname, name, login, password, email) VALUES (?,?,?,?,?);";
   private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD
   = "SELECT `id-user`, surname, name, login, password, email, discount, `role-id-role` FROM users WHERE login = ? AND password = ?;";  

   private static final String SQL_SELECT_ALL_USERS
   = "SELECT `id-user`, surname, name, login, password, email, discount, `role-id-role` FROM users";
   
   @Override
    public User signIn(String login, String password) throws DAOException {
        User user = null;

        if (login != null && login != "" && password != null && password != "") {
            ConnectionPool connectionPool = null;
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;							
            try {
            	connectionPool = ConnectionPool.getInstance();
                connectionPool.initPool();
                connection = connectionPool.takeConnection();

                ps = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);
                ps.setString(1, login);
                ps.setString(2, password);
                rs = ps.executeQuery();
                
                

                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt(1));
                    user.setSurname(rs.getString(2));
                    user.setName(rs.getString(3));
                    user.setLogin(rs.getString(4));
                    user.setPassword(rs.getString(5));
                    user.setEmail(rs.getString(6));
                    user.setDiscount(rs.getInt(7));
                    user.setRole(Role.getValue(rs.getInt(8)));
                }
              //  System.out.println(user.toString());

            }catch (SQLException | ConnectionPoolException e){
        	   throw new DAOException (e);
           }
           finally {
            try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
	         	   throw new DAOException (e);
			}
        }
        }
        return user;
    }

   
    @Override
    public void register(User user) throws DAOException {

    		ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connectionPool.initPool();
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_USER);
                ps.setString(1, user.getSurname());
                ps.setString(2, user.getName());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getEmail());
                ps.executeUpdate();
                connection.commit();
            } catch (SQLException | ConnectionPoolException e){
         	   throw new DAOException (e);
            } finally {
                try {
    				connectionPool.dispose();
    			} catch (ConnectionPoolException e) {
    	         	   throw new DAOException (e);
    			}
            }
         }
       
	@Override
	public List<User> findAll() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;
        List<User> users= new ArrayList<>();;
        try {
        	connectionPool.initPool();
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setSurname(rs.getString(2));
                user.setName(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setDiscount(rs.getInt(7));
                user.setRole(Role.getValue(rs.getInt(8)));
                users.add(user);
            }
        } catch (ConnectionPoolException | SQLException  e) {
            throw new DAOException("find all users exception ", e);
        } finally {
            try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
	         	   throw new DAOException (e);
			}
        }
        return users;		
	}
}