package PollingSystem.repository.user;

import PollingSystem.model.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepo {
    User getUserById(Integer id);
    List<User> getAllUsers();
    Integer createUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUserById(Integer id) throws Exception;
}
