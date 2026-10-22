package PollingSystem.repository.user;

import PollingSystem.mapper.user.UsesMapper;
import PollingSystem.model.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    private static final String USERS_TABLE_NAME = "users";

    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(Integer id) {
        String sql = "SELECT * FROM " + USERS_TABLE_NAME +
                    " WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new UsesMapper(),
                    id
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM " + USERS_TABLE_NAME;
        try {
            return jdbcTemplate.query(
                    sql,
                    new UsesMapper()
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer createUser(User user) throws Exception {
        String sql = "INSERT INTO " + USERS_TABLE_NAME +
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
        String sql = "UPDATE " + USERS_TABLE_NAME +
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
        String sql = "DELETE FROM " + USERS_TABLE_NAME +
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
