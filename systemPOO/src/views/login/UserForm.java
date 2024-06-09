package views.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.UserController;
import entities.User;

public class UserForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public UserForm(UserController userController, User userToUpdate) {
        initComponents(userController, userToUpdate);
    }

    private void initComponents(UserController userController, User userToUpdate) {
        setTitle(userToUpdate == null ? "Add User" : "Edit User");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        if (userToUpdate != null) {
            usernameField.setText(userToUpdate.getUsername());
            usernameField.setEditable(false); // Não permitir a edição do nome de usuário
        }

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel roleLabel = new JLabel("Role:");
        String[] roles = {"ADMIN", "USER"};
        roleComboBox = new JComboBox<>(roles);
        if (userToUpdate != null) {
            roleComboBox.setSelectedItem(userToUpdate.isAdmin() ? "ADMIN" : "USER");
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean isAdmin = roleComboBox.getSelectedItem().equals("ADMIN");
                User user = new User(username, password, isAdmin);
                try {
                    if (userToUpdate == null) {
                        userController.addUser(user);
                    } else {
                        userController.updateUser(user);
                    }
                    JOptionPane.showMessageDialog(UserForm.this, "User saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Fechar a janela após salvar
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserForm.this, "Error saving user: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(roleComboBox);
        add(new JLabel()); // Célula vazia
        add(saveButton);
    }
}
