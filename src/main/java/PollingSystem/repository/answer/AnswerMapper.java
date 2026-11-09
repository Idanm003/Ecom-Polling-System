package PollingSystem.repository.answer;

import PollingSystem.model.answer.Answer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Answer(
                rs.getInt("answer_id"),
                rs.getInt("user_id"),
                rs.getInt("question_id"),
                rs.getInt("selected_option")
        );
    }
}
