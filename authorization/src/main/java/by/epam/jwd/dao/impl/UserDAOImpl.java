package by.epam.jwd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.dbConnection.*;
import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.entity.Role;
import by.epam.jwd.entity.User;

public class UserDAOImpl implements UserDAO {

	private static final String SQL_INSERT_USER = "INSERT INTO users (surname, name, login, password, email) VALUES (?,?,?,?,?);";
	private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT `id-user`, surname, name, login, email, discount, `role-id-role` FROM users WHERE login = ? AND password = ?;";
	private static final String SQL_SELECT_ALL_USERS = "SELECT `id-user`, surname, name, login, password, email, discount, `role-id-role` FROM users";
	private static final String SQL_UPDATE_USER_DISCOUNT_BY_ID = "UPDATE users SET discount=? WHERE `id-user`=?;";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET surname=?, name=?, login=?, password=?, email=? WHERE `id-user`=?;";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT `id-user`, surname, name, login, email, discount, `role-id-role` FROM users WHERE `id-user` = ?;";
	private static final String SQL_UPDATE_USER_ROLE_BY_ID = "UPDATE users SET `role-id-role`=? WHERE `id-user`=?;";

	private ConnectionPool connectionPool;

	public UserDAOImpl() {
		connectionPool = ConnectionPool.getInstance();

		try {
			connectionPool.initPool();
		} catch (ConnectionPoolException e) {
			// TODO: logger
			System.out.println("pool not initialized");
		}
	}

	@Override
	public User signIn(String login, String password) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
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
				user.setEmail(rs.getString(6));
				user.setDiscount(rs.getInt(7));
				user.setRole(Role.getValue(rs.getInt(8)));
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return user;
	}

	@Override
	public void register(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connectionPool.initPool(); // разобраться, почему не работает без инициализации внутри метода (только
										// здесь)
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
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		User user = null;
		List<User> users = new ArrayList<>();
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SQL_SELECT_ALL_USERS);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setSurname(rs.getString(2));
				user.setName(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setEmail(rs.getString(6));
				user.setDiscount(rs.getInt(7));
				user.setRole(Role.getValue(rs.getInt(8)));
				users.add(user);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("find all users exception ", e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return users;
	}

	@Override
	public void setDiscountById(int id, int discount) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(SQL_UPDATE_USER_DISCOUNT_BY_ID);
			ps.setInt(1, discount);
			ps.setInt(2, id);
			ps.executeUpdate();
			connection.commit();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("user setDiscount exception ", e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void update(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
			ps.setString(1, user.getSurname());
			ps.setString(2, user.getName());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getEmail());
			ps.executeUpdate();
			connection.commit();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("user update exception ", e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public User getUserById(int id) throws DAOException {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setSurname(rs.getString(2));
				user.setName(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setEmail(rs.getString(6));
				user.setDiscount(rs.getInt(7));
				user.setRole(Role.getValue(rs.getInt(8)));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("user find by ID exception ", e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return user;
	}

	@Override
	public void setRoleById(int id, String role) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;
		int roleNumber = 1;
		if (role.equals("agent"))
			roleNumber = 2;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(SQL_UPDATE_USER_ROLE_BY_ID);
			ps.setInt(1, roleNumber);
			ps.setInt(2, id);
			ps.executeUpdate();
			connection.commit();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException("user setDiscount exception ", e);
		} finally {
			try {
				connectionPool.dispose();
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}
}
