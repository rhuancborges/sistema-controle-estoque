package controllers;

import java.util.List;

import dao.impl.UserDAOImpl;
import dao.interfaces.UserDAO;
import entities.User;
import util.UserManager;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImpl(); // Cria uma instância padrão de UserDAOImpl
    }
    
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticateUser(String username, String password) {
        return UserManager.authenticate(username, password);
    }

    public void addUser(User user) throws Exception {
        userDAO.addUser(user);
    }

    public User getUser(String username) throws Exception {
        return userDAO.getUser(username);
    }

    public List<User> getAllUsers() throws Exception {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) throws Exception {
        userDAO.updateUser(user);
    }
}
