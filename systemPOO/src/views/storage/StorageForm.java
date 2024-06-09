package views.storage;

import controllers.StockMovementController;
import entities.Product;
import entities.StockMovement;
import entities.enums.MovementType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class StorageForm extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField productField;
    private JTextField quantityField;
    private JComboBox<String> typeComboBox;
    private StockMovementController stockMovementController;

    public StorageForm() {
        this.stockMovementController = new StockMovementController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2));

        JLabel productLabel = new JLabel("Product:");
        productField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();

        JLabel typeLabel = new JLabel("Adjustment Type:");
        String[] types = {"ADD", "REMOVE"};
        typeComboBox = new JComboBox<>(types);

        JButton adjustButton = new JButton("Adjust Stock");
        adjustButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String productName = productField.getText();
                    Product product = new Product(productName); // Assuming Product constructor takes name only
                    double quantity = Double.parseDouble(quantityField.getText());
                    MovementType type = MovementType.valueOf((String) typeComboBox.getSelectedItem());

                    StockMovement movement = new StockMovement(LocalDate.now(), product, type, quantity);
                    stockMovementController.registerMovement(movement);

                    JOptionPane.showMessageDialog(StorageForm.this, "Stock adjusted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StorageForm.this, "Error adjusting stock: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
