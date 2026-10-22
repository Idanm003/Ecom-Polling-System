package PollingSystem.service.user;

import PollingSystem.model.user.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    List<User> getAllUsers();
    Integer createUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUserById(Integer integer) throws Exception;
}
