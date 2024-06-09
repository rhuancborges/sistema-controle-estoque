package views.product;

import controllers.ProductController;
import dao.impl.ProductDAOImpl;
import entities.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView extends JFrame {
	private static final long serialVersionUID = 1L;
	private ProductController productController;
    private JTable productTable;
    private DefaultTableModel tableModel;

    public ProductView() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        productController = new ProductController(productDAO);
        initComponents(productDAO);
    }

    private void initComponents(ProductDAOImpl productDAO) {
        setTitle("Product Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductForm(ProductView.this, productController).setVisible(true);
            }
        });

        JButton listButton = new JButton("List Products");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProducts();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);

        productTable = new JTable();
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Bar Code", "Name", "Price"}, 0
        );
        productTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(productTable);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadProducts() {
        try {
            List<Product> products = productController.getAllProducts();
            tableModel.setRowCount(0); // Clear existing rows
            for (Product product : products) {
                tableModel.addRow(new Object[]{
                    product.getId(), product.getBarCode(), product.getName(), product.getPrice()
                });
            }
            System.out.println("Products loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductView().setVisible(true);
            }
        });
    }
}
