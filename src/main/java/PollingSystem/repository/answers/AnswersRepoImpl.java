package PollingSystem.repository.answers;

import PollingSystem.mapper.answers.AnswersMapper;
import PollingSystem.model.answers.Answers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswersRepoImpl implements AnswersRepo {

    private static final String ANSWERS_TABLE_NAME = "answers";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Answers getAnswersByUserIdAndQuestionId(Integer userId, Integer questionId) {
        String sql = "SELECT * FROM " + ANSWERS_TABLE_NAME +
                    " WHERE user_id = ? AND question_id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new AnswersMapper(),
                    userId,
                    questionId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Answers> getAllAnswersByUserId(Integer userId) {
        String sql = "SELECT * FROM " + ANSWERS_TABLE_NAME +
                    " WHERE user_id = ?";
        return jdbcTemplate.query(
          sql,
          new AnswersMapper(),
          userId
        );
    }

    @Override
    public Integer countAllAnswersByUserId(Integer userId) {
        String sql = "SELECT COUNT(*) FROM " + ANSWERS_TABLE_NAME +
                    " WHERE user_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                userId
        );
    }

    @Override
    public Integer countAnswersByQuestionIdAndOptions(Integer questionId, Integer optionId) {
        String sql = "SELECT * FROM " +  ANSWERS_TABLE_NAME +
                    " WHERE question_id = ? AND option_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                questionId,
                optionId
        );
    }

    @Override
    public void createAnswers(Answers answers) throws Exception {
        String sql = "INSERT INTO" +  ANSWERS_TABLE_NAME +
                    " (user_id, question_id, selected_option) VALUES (?, ?, ?)";
        try{
            jdbcTemplate.update(
                    sql,
                    answers.getUserId(),
                    answers.getQuestionId(),
                    answers.getSelectedOption()
            );
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
