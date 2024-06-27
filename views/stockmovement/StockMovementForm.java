package views.stockmovement;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.StockMovementController;
import entities.Product;
import entities.Supplier;
import entities.enums.MovementType;

public class StockMovementForm extends JPanel {
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
        setLayout(new GridLayout(5, 2));

        JLabel productLabel = new JLabel("Product:");
        productField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();

        JLabel typeLabel = new JLabel("Movement Type:");
        String[] types = {"PURCHASE", "SALE", "ADJUSTMENT"}; // Atualizando tipos de movimentação
        typeComboBox = new JComboBox<>(types);

        JButton adjustButton = new JButton("Adjust Stock");
        adjustButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String productName = productField.getText();
                    Double quantity = Double.parseDouble(quantityField.getText());
                    MovementType type = MovementType.valueOf((String) typeComboBox.getSelectedItem());
                    

                    Product product = new Product(productName);

                    stockMovementController.registerMovement(product, type, quantity, LocalDate.now()); // Passando o fornecedor para o controller

                    JOptionPane.showMessageDialog(StockMovementForm.this, "Stock adjusted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

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
        add(new JLabel()); // Empty cell
        add(adjustButton);
    }
}
