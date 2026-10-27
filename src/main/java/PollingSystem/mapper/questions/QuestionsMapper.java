package PollingSystem.mapper.questions;

import PollingSystem.model.questions.Questions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsMapper implements RowMapper<Questions> {
    @Override
    public Questions mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Questions(
          rs.getInt("question_id"),
          rs.getString("question_title"),
          rs.getString("first_option"),
          rs.getString("second_option"),
          rs.getString("third_option"),
          rs.getString("fourth_option")
        );
    }
}
