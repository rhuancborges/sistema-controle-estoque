package views.supplier;

import controllers.SupplierController;
import entities.Supplier;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField idField;
    private JTextField cnpjField;
    private JTextField nameField;
    private SupplierController supplierController;
    private SupplierView parent;

    public SupplierForm(SupplierView parent, SupplierController supplierController) {
        this.parent = parent;
        this.supplierController = supplierController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Supplier");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel cnpjLabel = new JLabel("CNPJ:");
        cnpjField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer id = Integer.parseInt(idField.getText());
                    String cnpj = cnpjField.getText();
                    String name = nameField.getText();

                    Supplier supplier = new Supplier(id, cnpj, name);
                    supplierController.registerSupplier(supplier);
                    System.out.println("Supplier added successfully");
                    parent.loadSuppliers();
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
        add(new JLabel()); // empty cell
        add(addButton);
    }
}
