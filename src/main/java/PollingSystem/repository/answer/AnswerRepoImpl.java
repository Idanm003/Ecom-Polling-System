package PollingSystem.repository.answer;

import PollingSystem.config.PollTablesConfig;
import PollingSystem.model.answer.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerRepoImpl implements AnswerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PollTablesConfig pollTablesConfig;

    @Override
    public Answer getAnswerByUserIdAndQuestionId(Integer userId, Integer questionId) {
        String sql = "SELECT * FROM " + pollTablesConfig.getAnswers() +
                    " WHERE user_id = ? AND question_id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new AnswerMapper(),
                    userId,
                    questionId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Answer> getAllAnswersByUserId(Integer userId) {
        String sql = "SELECT * FROM " + pollTablesConfig.getAnswers() +
                    " WHERE user_id = ?";
        return jdbcTemplate.query(
          sql,
          new AnswerMapper(),
          userId
        );
    }

    @Override
    public Integer countAllAnswersByUserId(Integer userId) {
        String sql = "SELECT COUNT(*) FROM " + pollTablesConfig.getAnswers() +
                    " WHERE user_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                userId
        );
    }

    @Override
    public Integer countAllAnswersByQuestionId(Integer questionId) {
        String sql = "SELECT COUNT(*) FROM " + pollTablesConfig.getAnswers() +
                    " WHERE question_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                questionId
        );
    }

    @Override
    public Integer countAnswersByQuestionIdAndOptions(Integer questionId, Integer selectedOption) {
        String sql = "SELECT COUNT(*) FROM " + pollTablesConfig.getAnswers() +
                    " WHERE question_id = ? AND selected_option = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                questionId,
                selectedOption
        );
    }

    @Override
    public void createAnswer(Answer answer) throws Exception {
        String sql = "INSERT INTO " + pollTablesConfig.getAnswers() +
                    " (user_id, question_id, selected_option) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(
                    sql,
                    answer.getUserId(),
                    answer.getQuestionId(),
                    answer.getSelectedOption()
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteAllAnswersByUserId(Integer userId) throws Exception {
        String sql = "DELETE FROM " + pollTablesConfig.getAnswers() +
                    " WHERE user_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    userId
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteAllAnswersByQuestionId(Integer questionId) throws Exception {
        String sql = "DELETE FROM " + pollTablesConfig.getAnswers() +
                    " WHERE question_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    questionId
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
