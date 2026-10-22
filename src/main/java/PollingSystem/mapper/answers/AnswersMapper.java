package PollingSystem.mapper.answers;

import PollingSystem.model.answers.Answers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswersMapper implements RowMapper<Answers> {
    @Override
    public Answers mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Answers(
                rs.getInt("answer_id"),
                rs.getInt("user_id"),
                rs.getInt("question_id"),
                rs.getInt("selected_option")
        );
    }
}
