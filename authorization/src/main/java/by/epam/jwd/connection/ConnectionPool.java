package by.epam.jwd.connection;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.epam.jwd.dao.exception.DAOException;

public final class ConnectionPool {
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;
    private static ConnectionPool instance;
    
    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }

    }
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    
    public void initPool() throws DAOException, ConnectionPoolException {

        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        }  catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }
  

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() throws ConnectionPoolException {
     try {
            closeConnectionQueue(connectionQueue);
            closeConnectionQueue(givenAwayConQueue);

        } catch (SQLException e) {
            throw new ConnectionPoolException("Exception during closing operation!", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Connection isn't return to the pool", e);
        }
        try {
            rs.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "ResultSet isn't closed.", e);
        }
        try {
            st.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Statement isn't closed.", e);
        }
    }

//    public void closeConnection(Connection con, Statement st) {
//        try {
//            con.close();
//        } catch (SQLException e) {
//            //logger.log(Level.ERROR, "Connection isn't return to the pool", e);
//        }
//        try {
//            st.close();
//        } catch (SQLException e) {
//            //logger.log(Level.ERROR, "Statement isn't closed.", e);
//        }
//    }

    
    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
    	Connection connection = null;

        while ((connection = queue.poll()) != null) {
            connection.close();
        }
        queue.clear();
    }
}
