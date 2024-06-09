package views.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controllers.UserController;
import entities.User;

public class UserView extends JPanel {
    private static final long serialVersionUID = 1L;
    private UserController userController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox adminCheckBox;

    public UserView(UserController userController) {
        this.userController = userController;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel adminLabel = new JLabel("Admin:");
        adminCheckBox = new JCheckBox();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean isAdmin = adminCheckBox.isSelected();
                User user = new User(username, password, isAdmin);
                try {
                    userController.addUser(user);
                    JOptionPane.showMessageDialog(UserView.this, "User added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserView.this, "Error adding user: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton listButton = new JButton("List Users");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<User> users = userController.getAllUsers();
                    StringBuilder userList = new StringBuilder();
                    for (User user : users) {
                        userList.append(user.getUsername()).append("\n");
                    }
                    JOptionPane.showMessageDialog(UserView.this, userList.toString(), "User List", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserView.this, "Error listing users: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(adminLabel);
        add(adminCheckBox);
        add(listButton);
        add(saveButton);
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        adminCheckBox.setSelected(false);
    }
}

