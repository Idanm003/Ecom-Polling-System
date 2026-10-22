package PollingSystem.repository.questions;

import PollingSystem.mapper.questions.QuestionsMapper;
import PollingSystem.model.questions.Questions;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsRepoImpl implements QuestionsRepo {

    private static final String QUESTIONS_TABLE_NAME = "questions";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Questions getQuestionsById(Integer id) {
        String sql = "SELECT * FROM " + QUESTIONS_TABLE_NAME +
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
        String sql = "SELECT * FROM " + QUESTIONS_TABLE_NAME;
        return jdbcTemplate.query(
                sql,
                new QuestionsMapper()
        );
    }

    @Override
    public Integer createQuestion(Questions questions) throws Exception {
        String sql = "INSERT INTO" + QUESTIONS_TABLE_NAME +
                    " (title, first_option, second_option, third_option, fourth_option)" +
                    " VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                sql,
                questions.getOption1(),
                questions.getOption2(),
                questions.getOption3(),
                questions.getOption4()
            );
                return jdbcTemplate.queryForObject(
                    "SELECT LAST_INSERT_ID()",
                    Integer.class
                );
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void updateQuestion(Questions questions) throws Exception {
        String sql = "UPDATE " + QUESTIONS_TABLE_NAME +
                    " SET title = ?, first_option = ?, second_option = ?, third_option = ?, fourth_option = ?" +
                    " WHERE id = ?";
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
        String sql =  "DELETE FROM " + QUESTIONS_TABLE_NAME +
                    " WHERE id = ?";
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
