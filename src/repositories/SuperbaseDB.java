package repositories;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperbaseDB implements IDB {
    private Connection connection;

    private String url;
    private String user;
    private String password;

    public SuperbaseDB(String url, String user, String password) {

    }

    @Override
    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            connection = DriverManager.getConnection(url, user, password);

            return connection;

        } catch (Exception e) {
            System.out.println("Failed to connect to SuperbaseDB");

            return null;
        }
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection close error" + e.getMessage());
            }
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
