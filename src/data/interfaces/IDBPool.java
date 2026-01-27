package data.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBPool {
    Connection getConnection() throws SQLException;
    void releaseConnection(Connection connection);
    void shutdown();
}
