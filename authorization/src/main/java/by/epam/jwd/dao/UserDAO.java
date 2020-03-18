package by.epam.jwd.dao;

import java.util.List;

import by.epam.jwd.dao.exception.DAOException;
import by.epam.jwd.entity.User;

public interface UserDAO {

    User signIn(String login, String password)throws DAOException;
    
    void register(User user)throws DAOException;

    List<User> findAll () throws DAOException;

}