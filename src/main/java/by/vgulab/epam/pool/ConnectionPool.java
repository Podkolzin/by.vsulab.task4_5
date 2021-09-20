package by.vgulab.epam.pool;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public final class ConnectionPool {

//    private String jdbcUrl = "jdbc:mysql://localhost:3306/geotur";
//    private String user = "root";
//    private String password = "root1234";
//    int connectTimeOut = 30;
//    int maxSize = 20;
    private String jdbcUrl;
    private String user;
    private String password;
    int connectTimeOut;
    int maxSize;

    private Queue<Connection> freeConnection = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnection = new ConcurrentSkipListSet<>((c1, c2) -> Integer.compare(c1.hashCode(),
            c2.hashCode()));

    private ConnectionPool() {
    }

    public void init(String driverClass, String jdbcUrl, String user, String password, int minSize, int maxSize, int connectTimeOut)
            throws ConnectionPoolException {
        try {
            destroy();
            Driver driver = (Driver) Class.forName(driverClass).getConstructor().newInstance();
            DriverManager.registerDriver(driver);

            this.jdbcUrl = jdbcUrl;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.connectTimeOut = connectTimeOut;

            for (int i = 0; i < minSize; i++) {
                freeConnection.add(newConnection());
            }

        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(e);
        }


    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnection.poll();
                if (connection != null) {
                    if (connection.isValid(0)) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnection.size() < maxSize) {
                    connection = newConnection();
                } else {
                    throw new ConnectionPoolException("Database connections number bad size ");
                }

            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }

        usedConnection.add(connection);
        return new ConnectionWrapper(connection);
    }

    public void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            usedConnection.remove(connection);
            freeConnection.add(connection);
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
            }
        }
    }

    public void destroy() {
        synchronized (freeConnection) {
            synchronized (usedConnection) {
                usedConnection.addAll(freeConnection);
                freeConnection.clear();
                for (Connection connection : usedConnection) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
                usedConnection.clear();
            }
        }

    }

    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }
}
