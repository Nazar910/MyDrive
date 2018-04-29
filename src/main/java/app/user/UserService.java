package app.user;

import java.util.Collection;

import app.models.User;

public interface UserService {
    public User findByUserName(String username);
    public Collection<User> findAll();
    public void createUser(User user);
}