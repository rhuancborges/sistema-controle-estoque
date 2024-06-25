package views.stockmovement;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.StockMovementController;
import entities.Product;
import entities.Supplier;
import entities.enums.MovementType;

public class StockMovementForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField productField;
    private JTextField quantityField;
    private JComboBox<String> typeComboBox;
    private JTextField supplierField; // Adicionando campo do fornecedor
    private StockMovementController stockMovementController;

    public StockMovementForm(StockMovementController stockMovementController) {
        this.stockMovementController = stockMovementController;
        initComponents();
    }

    public StockMovementForm() {
	}

	private void initComponents() {
        setTitle("Stock Movement");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel productLabel = new JLabel("Product:");
        productField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();

        JLabel typeLabel = new JLabel("Movement Type:");
        String[] types = {"PURCHASE", "SALE", "ADJUSTMENT"}; // Atualizando tipos de movimentação
        typeComboBox = new JComboBox<>(types);

        JLabel supplierLabel = new JLabel("Supplier ID:"); // Adicionando label do fornecedor
        supplierField = new JTextField();

        JButton adjustButton = new JButton("Adjust Stock");
        adjustButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String productName = productField.getText();
                    Double quantity = Double.parseDouble(quantityField.getText());
                    MovementType type = MovementType.valueOf((String) typeComboBox.getSelectedItem());
                    
                    Integer supplierId = Integer.parseInt(supplierField.getText()); // Obtendo ID do fornecedor como um inteiro
                    String supplierName = supplierField.getText(); // Obtendo nome do fornecedor
                    String supplierCnpj = supplierField.getText();
                    Supplier supplier = new Supplier(supplierId, supplierCnpj, supplierName); // Criando o objeto fornecedor

                    Product product = new Product(productName);

                    stockMovementController.registerMovement(product, type, quantity, LocalDate.now(), supplier); // Passando o fornecedor para o controller

                    JOptionPane.showMessageDialog(StockMovementForm.this, "Stock adjusted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StockMovementForm.this, "Error adjusting stock: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(productLabel);
        add(productField);
        add(quantityLabel);
        add(quantityField);
        add(typeLabel);
        add(typeComboBox);
        add(supplierLabel); // Adicionando label do fornecedor
        add(supplierField); // Adicionando campo do fornecedor
        add(new JLabel()); // Empty cell
        add(adjustButton);
    }
}
