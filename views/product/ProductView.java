package views.product;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.ProductController;
import controllers.StockMovementController;
import entities.Product;
import views.stockmovement.StockMovementForm;

public class ProductView extends JPanel {
    private static final long serialVersionUID = 1L;
    private ProductController productController;
    private StockMovementController stockMovementController;
    private JTable productTable;
    private DefaultTableModel productTableModel;

    public ProductView(ProductController productController, StockMovementController stockMovementController) {
        this.productController = productController;
        this.stockMovementController = stockMovementController;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Inicializando o buttonPanel
        JPanel buttonPanel = new JPanel();

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductForm();
            }
        });

        JButton addMovementButton = new JButton("Add Movement");
        addMovementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMovementForm();
            }
        });

        buttonPanel.add(addProductButton);
        buttonPanel.add(addMovementButton);

        // Adicionando o buttonPanel ao painel principal
        add(buttonPanel, BorderLayout.SOUTH);

        // Inicializando a tabela de produtos
        productTableModel = new DefaultTableModel(new String[] { "ID", "Barcode", "Name", "Price", "Quantity" }, 0);
        productTable = new JTable(productTableModel);

        add(productTable.getTableHeader(), BorderLayout.NORTH);
        add(productTable, BorderLayout.CENTER);

        refreshProducts();
    }

    private void openProductForm() {
        ProductForm productForm = new ProductForm(productController);
        JOptionPane.showMessageDialog(this, productForm, "Add Product", JOptionPane.PLAIN_MESSAGE);
        refreshProducts(); // Refresh products after form is closed
    }

    private void openMovementForm() {
        StockMovementForm movementForm = new StockMovementForm(stockMovementController);
        movementForm.setVisible(true);
    }

    private void refreshProducts() {
        productTableModel.setRowCount(0);
        try {
            List<Product> products = productController.getAllProducts();
            for (Product product : products) {
                Object[] row = { product.getId(), product.getBarCode(), product.getName(), product.getPrice(),
                        product.getQuantity() };
                productTableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
