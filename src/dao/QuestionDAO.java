package dao;

import entity.Question;
import dao.DBConnection;
import entity.Option;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

public class QuestionDAO {
        // Load ALL questions from DB
    public List<Question> getAllQuestions() throws SQLException{
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Question";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setText(rs.getString("text"));
                q.setCategory(rs.getString("category"));
                q.setDifficulty(rs.getString("difficulty"));
                q.setOptions(getOptionsFor(q.getId(), con));
                questions.add(q);
            }
        }
        return questions;
    }

    private List<Option> getOptionsFor(int questionId, Connection con) throws SQLException {
        List<Option> opts = new ArrayList<>();
        String sql = "SELECT * FROM Options WHERE question_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Option o = new Option();
                o.setId(rs.getInt("id"));
                o.setText(rs.getString("text"));
                o.setIsCorrect(rs.getBoolean("is_correct"));
                opts.add(o);
            }
        }
        return opts;
    }
}
