package views.storage;

import controllers.StockMovementController;
import entities.StockMovement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StorageView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable movementTable;
    private DefaultTableModel tableModel;
    private StockMovementController stockMovementController;

    public StorageView() {
        this.stockMovementController = new StockMovementController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadMovements();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StorageView.this, "Error loading movements: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);

        movementTable = new JTable();
        tableModel = new DefaultTableModel(
                new Object[]{"Date", "Product", "Type", "Quantity"}, 0
        );
        movementTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(movementTable);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        try {
            loadMovements();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading movements: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadMovements() throws Exception {
        List<StockMovement> movements = stockMovementController.getAllMovements();
        tableModel.setRowCount(0); // Clear existing rows
        for (StockMovement movement : movements) {
            tableModel.addRow(new Object[]{
                    movement.getDate(), movement.getProduct().getName(), movement.getType(), movement.getQuantity()
            });
        }
        System.out.println("Movements loaded successfully");
    }
}
