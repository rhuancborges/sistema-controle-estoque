package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.UserDAO;
import entities.User;

public class UserDAOImpl implements UserDAO {
    private List<User> users;

    public UserDAOImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    @Override
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                return;
            }
        }
    }

}