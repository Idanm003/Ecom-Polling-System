package PollingSystem.controller.user;

import PollingSystem.model.user.User;
import PollingSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public Object getUserById(@PathVariable(value = "id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "Error: User with id " + id + " not found";
        }
        return user;
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/create")
    public Object createUser(@RequestBody User user) {
        try {
            return userService.createUser(user);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping(value = "/update")
    public String updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return "User with id " + user.getUserId() + " updated successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable(value = "id") Integer id) {
        try {
            userService.deleteUserById(id);
            return "User with id " + id + " and all of their answers deleted successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}