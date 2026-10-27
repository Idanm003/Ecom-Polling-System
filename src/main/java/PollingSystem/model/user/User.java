package PollingSystem.model.user;

import java.time.LocalDate;

public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String address;
    private LocalDate joinedDate;

    public User() {}

    public User(Integer userId, String firstName, String lastName, String email, Integer age, String address, LocalDate joinedDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.address = address;
        this.joinedDate = joinedDate;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public Integer getAge() {
        return this.age;
    }
    public String getAddress() {
        return this.address;
    }
    public LocalDate getJoinedDate() {
        return this.joinedDate;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }
}
