package dao;
import entity.Submission;
import java.sql.*;

import java.util.*;

public class SubmissionDAO {
    public void saveSubmission(int studentId, int score, float pct, int timeSec) throws SQLException {
        String sql = "INSERT INTO Submission (student_id, score, percentage, time_seconds) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, score);
            ps.setFloat(3, pct);
            ps.setInt(4, timeSec);
            ps.executeUpdate();
        }
    }

    public List<Submission> getLeaderboard() throws SQLException {
        List<Submission> list = new ArrayList<>();
        // The required JOIN query
        String sql = "SELECT s.name, sub.score, sub.percentage, sub.time_seconds " +
                     "FROM Submission sub JOIN Student s ON sub.student_id = s.id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Submission sub = new Submission(
                    rs.getString("name"),
                    rs.getInt("score"),
                    rs.getFloat("percentage"),
                    rs.getInt("time_seconds")
                );
                list.add(sub);
            }
        }
        return list;
    }
}
