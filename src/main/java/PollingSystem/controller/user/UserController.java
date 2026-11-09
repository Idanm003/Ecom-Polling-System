package PollingSystem.controller.user;

import PollingSystem.model.user.User;
import PollingSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable(value = "id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @PutMapping(value = "/update")
    public String updateUser(@RequestBody User user) throws Exception {
        userService.updateUser(user);
        return "User with id " + user.getUserId() + " updated successfully";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable(value = "id") Integer id) throws Exception {
        userService.deleteUserById(id);
        return "User with id " + id + " and all of their answers deleted successfully";
    }
}