package PollingSystem.service.user;

import PollingSystem.exceptions.NotFoundException;
import PollingSystem.model.user.User;
import PollingSystem.repository.answer.AnswerRepo;
import PollingSystem.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AnswerRepo answerRepo;

    private void validateUser(User user) throws Exception {
        if (user.getFirstName() == null
            || user.getFirstName().isBlank()
            || user.getLastName() == null
            || user.getLastName().isBlank()) {
            throw new Exception("User first name and last name are required");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new Exception("User email is missing or invalid");
        }
        if (user.getAge() == null || user.getAge() <= 0) {
            throw new Exception("User age must be a positive number");
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepo.getUserById(id);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public Integer createUser(User user) throws Exception {
        validateUser(user);
        if (user.getAddress() == null) {
            user.setAddress("");
        }
        if (user.getJoinedDate() == null) {
            user.setJoinedDate(LocalDate.now());
        }
        return userRepo.createUser(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        if (user.getUserId() == null) {
            throw new Exception("User id is missing");
        }
        User existingUser = getUserById(user.getUserId());
        validateUser(user);
        if (user.getAddress() == null) {
            user.setAddress(existingUser.getAddress());
        }
        if (user.getJoinedDate() == null) {
            user.setJoinedDate(existingUser.getJoinedDate());
        }
        userRepo.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) throws Exception {
        getUserById(id); // throws 404 if missing
        answerRepo.deleteAllAnswersByUserId(id);
        userRepo.deleteUserById(id);
    }
}