package PollingSystem.repository.question;

import PollingSystem.config.PollTablesConfig;
import PollingSystem.model.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepoImpl implements QuestionRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PollTablesConfig pollTablesConfig;

    @Override
    public Question getQuestionById(Integer id) {
        String sql = "SELECT * FROM " + pollTablesConfig.getQuestions() +
                    " WHERE question_id = ?";
        try {
            return jdbcTemplate.queryForObject(
              sql,
              new QuestionMapper(),
              id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM " + pollTablesConfig.getQuestions();
        return jdbcTemplate.query(
                sql,
                new QuestionMapper()
        );
    }

    @Override
    public Integer createQuestion(Question question) throws Exception {
        String sql = "INSERT INTO " + pollTablesConfig.getQuestions() +
                    " (question_title, first_option, second_option, third_option, fourth_option)" +
                    " VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                sql,
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
            );
            return jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()",
                Integer.class
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateQuestion(Question question) throws Exception {
        String sql = "UPDATE " + pollTablesConfig.getQuestions() +
                    " SET question_title = ?, first_option = ?, second_option = ?, third_option = ?, fourth_option = ?" +
                    " WHERE question_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4(),
                    question.getQuestionId()
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteQuestion(Integer id) throws Exception {
        String sql =  "DELETE FROM " + pollTablesConfig.getQuestions() +
                    " WHERE question_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    id
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
