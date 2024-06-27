package views.supplier;

import controllers.SupplierController;
import entities.Supplier;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierForm extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField idField;
    private JTextField cnpjField;
    private JTextField nameField;
    private JTextField emailField;
    private SupplierController supplierController;
    private SupplierView parent;

    public SupplierForm(SupplierView parent, SupplierController supplierController) {
        this.parent = parent;
        this.supplierController = supplierController;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel cnpjLabel = new JLabel("CNPJ:");
        cnpjField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email");
        emailField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer id = Integer.parseInt(idField.getText());
                    String cnpj = cnpjField.getText();
                    String name = nameField.getText();
                    String email = emailField.getText();

                    Supplier supplier = new Supplier(id, cnpj, name, email);
                    supplierController.registerSupplier(supplier);
                    JOptionPane.showMessageDialog(SupplierForm.this, "Supplier added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parent.refreshSuppliers();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SupplierForm.this, "Error adding supplier: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(idLabel);
        add(idField);
        add(cnpjLabel);
        add(cnpjField);
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(new JLabel());
        add(addButton);
    }
}
