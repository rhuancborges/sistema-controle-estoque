package util;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final List<User> users;

    static {
        users = new ArrayList<>();
        startUser();
    }

    public static void startUser(){
        users.add(new User("admin", "admin", true));
        users.add(new User("user", "user", false));
    }

    public static boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdmin(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.isAdmin()) {
                return true;
            }
        }
        return false;
    }
}
