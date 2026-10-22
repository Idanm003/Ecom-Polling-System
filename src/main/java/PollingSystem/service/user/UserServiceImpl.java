package PollingSystem.service.user;

import PollingSystem.model.user.User;
import PollingSystem.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public User getUserById(Integer id) {
        return userRepo.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public Integer createUser(User user) throws Exception {
        if (user.getUserId() == null) {
            throw new Exception("User with id " + user.getUserId() + " does not exist");
        }
        return userRepo.createUser(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        User existingUser = userRepo.getUserById(user.getUserId());
        if (existingUser == null) {
            throw new Exception("User with id " + user.getUserId() + " does not exist");
        } else  {
            userRepo.updateUser(user);
        }
    }

    @Override
    public void deleteUserById(Integer id) throws Exception {
        userRepo.deleteUserById(id);
    }
}
