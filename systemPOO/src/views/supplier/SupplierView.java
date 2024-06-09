package views.supplier;

import controllers.SupplierController;
import entities.Supplier;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierView extends JFrame {
	private static final long serialVersionUID = 1L;
	private SupplierController supplierController;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public SupplierView() {
        supplierController = new SupplierController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Supplier Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addButton = new JButton("Add Supplier");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SupplierForm(SupplierView.this, supplierController).setVisible(true);
            }
        });

        JButton listButton = new JButton("List Suppliers");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSuppliers();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);

        supplierTable = new JTable();
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "CNPJ", "Name"}, 0
        );
        supplierTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(supplierTable);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadSuppliers() {
        try {
            List<Supplier> suppliers = supplierController.getAllSuppliers();
            tableModel.setRowCount(0); // Clear existing rows
            for (Supplier supplier : suppliers) {
                tableModel.addRow(new Object[]{
                    supplier.getId(), supplier.getCnpj(), supplier.getName()
                });
            }
            System.out.println("Suppliers loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SupplierView().setVisible(true);
            }
        });
    }
}
