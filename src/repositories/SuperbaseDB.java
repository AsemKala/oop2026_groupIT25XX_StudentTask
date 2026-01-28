package repositories;

import data.interfaces.IDBPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuperbaseDB implements IDBPool {
    private static SuperbaseDB instance;
    private final List<Connection> availableConnections;
    private final List<Connection> usedConnections;
    private final int MAX_POOL_SIZE = 10;
    private final int INITIAL_POOL_SIZE = 5;

    private final String url;
    private final String user;
    private final String password;

    private SuperbaseDB() {
        this.url = "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require";
        this.user = "postgres.jrosmhhjxqglqwbovgle";
        this.password = "Q2Fnxn3X1Oy2Gied";

        availableConnections = new ArrayList<>(INITIAL_POOL_SIZE);
        usedConnections = new ArrayList<>();

        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                availableConnections.add(createConnection());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    public static synchronized SuperbaseDB getInstance() {
        if (instance == null) {
            instance = new SuperbaseDB();
        }
        return instance;
    }

    private Connection createConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                availableConnections.add(createConnection());
            } else {
                throw new SQLException("Maximum pool size reached, no available connections");
            }

        }

        Connection connection = availableConnections.removeLast();

        if (connection.isClosed()) {
            connection = createConnection();
        }

        usedConnections.add(connection);
        return connection;
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        if (connection != null && usedConnections.remove(connection)) {
            availableConnections.add(connection);
        }
    }

    @Override
    public synchronized void shutdown() {
        usedConnections.forEach(this::closeConnection);
        availableConnections.forEach(this::closeConnection);
        usedConnections.clear();
        availableConnections.clear();
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection close error" + e.getMessage());
        }
    }
}
