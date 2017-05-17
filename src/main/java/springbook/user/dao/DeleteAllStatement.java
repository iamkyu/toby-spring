package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Kj Nam
 * @since 2017-05-18
 */
public class DeleteAllStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
        return c.prepareStatement("DELETE FROM users");
    }
}
