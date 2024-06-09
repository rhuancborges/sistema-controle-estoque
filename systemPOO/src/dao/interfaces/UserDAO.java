package dao.interfaces;

import java.util.List;

import entities.User;

public interface UserDAO {
    void addUser(User user) throws Exception;
    User getUser(String username) throws Exception;
    List<User> getAllUsers() throws Exception;
	void updateUser(User user);
}