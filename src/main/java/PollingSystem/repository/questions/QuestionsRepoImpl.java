package PollingSystem.repository.questions;

import PollingSystem.config.PollTablesConfig;
import PollingSystem.mapper.questions.QuestionsMapper;
import PollingSystem.model.questions.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsRepoImpl implements QuestionsRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PollTablesConfig pollTablesConfig;

    @Override
    public Questions getQuestionsById(Integer id) {
        String sql = "SELECT * FROM " + pollTablesConfig.getQuestions() +
                    " WHERE question_id = ?";
        try {
            return jdbcTemplate.queryForObject(
              sql,
              new QuestionsMapper(),
              id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Questions> getAllQuestions() {
        String sql = "SELECT * FROM " + pollTablesConfig.getQuestions();
        return jdbcTemplate.query(
                sql,
                new QuestionsMapper()
        );
    }

    @Override
    public Integer createQuestion(Questions questions) throws Exception {
        String sql = "INSERT INTO " + pollTablesConfig.getQuestions() +
                    " (question_title, first_option, second_option, third_option, fourth_option)" +
                    " VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                sql,
                questions.getQuestionTitle(),
                questions.getOption1(),
                questions.getOption2(),
                questions.getOption3(),
                questions.getOption4()
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
    public void updateQuestion(Questions questions) throws Exception {
        String sql = "UPDATE " + pollTablesConfig.getQuestions() +
                    " SET question_title = ?, first_option = ?, second_option = ?, third_option = ?, fourth_option = ?" +
                    " WHERE question_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    questions.getQuestionTitle(),
                    questions.getOption1(),
                    questions.getOption2(),
                    questions.getOption3(),
                    questions.getOption4(),
                    questions.getQuestionId()
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
