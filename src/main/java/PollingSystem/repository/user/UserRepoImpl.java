package PollingSystem.repository.user;

import PollingSystem.config.PollTablesConfig;
import PollingSystem.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PollTablesConfig pollTablesConfig;

    @Override
    public User getUserById(Integer id) {
        String sql = "SELECT * FROM " + pollTablesConfig.getUsers() +
                    " WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new UserMapper(),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM " + pollTablesConfig.getUsers();
        return jdbcTemplate.query(
                sql,
                new UserMapper()
        );
    }

    @Override
    public Integer createUser(User user) throws Exception {
        String sql = "INSERT INTO " + pollTablesConfig.getUsers() +
                " (first_name, last_name, email, age, address, joined_date)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                    sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getAge(),
                    user.getAddress(),
                    user.getJoinedDate()
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
    public void updateUser(User user) throws Exception {
        String sql = "UPDATE " + pollTablesConfig.getUsers() +
                    " SET first_name = ?, last_name = ?, email = ?, age = ?, address = ?, joined_date = ?" +
                    " WHERE user_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getAge(),
                    user.getAddress(),
                    user.getJoinedDate(),
                    user.getUserId()
            );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteUserById(Integer id) throws Exception {
        String sql = "DELETE FROM " + pollTablesConfig.getUsers() +
                    " WHERE user_id = ?";
        try {
            jdbcTemplate.update(
                    sql,
                    id
            );
        }  catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
