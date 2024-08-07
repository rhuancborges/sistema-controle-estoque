package views.stockmovement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.StockMovementController;
import entities.StockMovement;
import views.product.ProductView;

public class StockMovementView extends JPanel {
    private static final long serialVersionUID = 1L;
    private StockMovementController stockMovementController;
    private ProductView productView;
    private JTable stockMovementTable;
    private DefaultTableModel stockMovementTableModel;

    public StockMovementView(StockMovementController stockMovementController) {
        this.stockMovementController = stockMovementController;
        initComponents();
    }
    
    public void openMovementForm() {
        StockMovementForm movementForm = new StockMovementForm(stockMovementController);
        JOptionPane.showMessageDialog(this, movementForm, "Add Stock Movement", JOptionPane.PLAIN_MESSAGE);
        refreshStockMovements();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        stockMovementTableModel = new DefaultTableModel(new String[] { "ID", "Product ID", "Type", "Quantity", "Date" }, 0);
        stockMovementTable = new JTable(stockMovementTableModel);

        add(stockMovementTable.getTableHeader(), BorderLayout.NORTH);
        add(stockMovementTable, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addMovementButton = new JButton("Add Movement");
        addMovementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMovementForm();
            }
        });

        buttonPanel.add(addMovementButton);

        add(buttonPanel, BorderLayout.SOUTH);

        refreshStockMovements();
    }

    public void refreshStockMovements() {
        stockMovementTableModel.setRowCount(0);
        try {
            List<StockMovement> movements = stockMovementController.getAllMovements();
            for (StockMovement movement : movements) {
                Object[] row = {movement.getId(), movement.getProduct().getId(), movement.getType(),
                        movement.getQuantity(), movement.getDate()}; // Adiciona o nome do fornecedor
                stockMovementTableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading stock movements: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public StockMovementController getStockMovementController(){
        return stockMovementController;
    }
}
