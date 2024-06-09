package views.product;

import controllers.ProductController;
import entities.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductForm extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField idField;
    private JTextField barCodeField;
    private JTextField nameField;
    private JTextField priceField;
    private ProductController productController;

    public ProductForm(ProductController productController) {
        this.productController = productController;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel barCodeLabel = new JLabel("Bar Code:");
        barCodeField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer id = Integer.parseInt(idField.getText());
                    String barCode = barCodeField.getText();
                    String name = nameField.getText();
                    Double price = Double.parseDouble(priceField.getText());

                    Product product = new Product(id, barCode, name, price);
                    productController.registerProduct(product);
                    JOptionPane.showMessageDialog(ProductForm.this, "Product added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ProductForm.this, "Price or ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProductForm.this, "Error adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(idLabel);
        add(idField);
        add(barCodeLabel);
        add(barCodeField);
        add(nameLabel);
        add(nameField);
        add(priceLabel);
        add(priceField);
        add(new JLabel()); // empty cell
        add(addButton);
    }
}
