package dao;

import entities.User;

public interface UserDAO {
    void saveUser(User user);
    void updateUser(User user);
}
