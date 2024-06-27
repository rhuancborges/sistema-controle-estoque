package application;

import javax.swing.SwingUtilities;

import controllers.UserController;
import dao.impl.UserDAOImpl;
import util.UserManager;
import views.login.LoginView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserManager.startUser();
                UserController userController = new UserController(new UserDAOImpl());
                new LoginView(userController).setVisible(true);
            }
        });
    }
}
