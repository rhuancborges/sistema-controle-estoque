package views.product;

import controllers.ProductController;
import entities.Product;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField idField;
    private JTextField barCodeField;
    private JTextField nameField;
    private JTextField priceField;
    private ProductController productController;
    private ProductView parent;

    public ProductForm(ProductView parent, ProductController productController) {
        this.parent = parent;
        this.productController = productController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Product");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                    product.setBarCode(barCode);
                    productController.registerProduct(product);
                    System.out.println("Product added successfully");
                    parent.loadProducts();
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
