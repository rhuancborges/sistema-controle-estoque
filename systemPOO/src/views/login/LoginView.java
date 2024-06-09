package views.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controllers.ProductController;
import controllers.StockMovementController;
import controllers.SupplierController;
import controllers.UserController;
import dao.impl.ProductDAOImpl;
import dao.impl.StockMovementDAOImpl;
import dao.impl.SupplierDAOImpl;
import dao.impl.UserDAOImpl;

public class LoginView extends JFrame {
    private static final long serialVersionUID = 1L;
    private UserController userController;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginView(UserController userController) {
        this.userController = userController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (userController.authenticateUser(username, password)) {
                    openMainView(); // Abre a MainView após o login bem-sucedido
                    dispose(); // Fecha a janela de login após o login bem-sucedido
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
    }

    private void openMainView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserController userController = new UserController(new UserDAOImpl());
                ProductController productController = new ProductController(new ProductDAOImpl());
                StockMovementController stockMovementController = new StockMovementController(new StockMovementDAOImpl());
                SupplierController supplierController = new SupplierController(new SupplierDAOImpl());
                new MainView(userController, productController, stockMovementController, supplierController).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserController userController = new UserController(new UserDAOImpl());
                new LoginView(userController).setVisible(true);
            }
        });
    }
}
