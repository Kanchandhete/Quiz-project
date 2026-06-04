package dao;

import java.sql.SQLException;
import java.sql.*;

public class StudentDAO {
    public int insertStudent(String name) throws SQLException{
        String sql = "INSERT INTO Student (name) VALUES (?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        }
        return -1;
    }
}
