package PollingSystem.repository.question;

import PollingSystem.model.question.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(
          rs.getInt("question_id"),
          rs.getString("question_title"),
          rs.getString("first_option"),
          rs.getString("second_option"),
          rs.getString("third_option"),
          rs.getString("fourth_option")
        );
    }
}
