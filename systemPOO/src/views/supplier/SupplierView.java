package views.supplier;

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
import controllers.SupplierController;
import entities.Supplier;

public class SupplierView extends JPanel {
    private static final long serialVersionUID = 1L;
    private SupplierController supplierController;
    private JTable supplierTable;
    private DefaultTableModel supplierTableModel;

    public SupplierView(SupplierController supplierController) {
        this.supplierController = supplierController;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        supplierTableModel = new DefaultTableModel(new String[] { "ID", "Name", "Contact" }, 0);
        supplierTable = new JTable(supplierTableModel);

        JScrollPane scrollPane = new JScrollPane(supplierTable);
        scrollPane.setPreferredSize(new Dimension(580, 350));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addSupplierButton = new JButton("Add Supplier");
        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSupplierForm(); // Chama o método para abrir o formulário ao clicar no botão "Add Supplier"
            }
        });
        buttonPanel.add(addSupplierButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshSuppliers();
    }

    private void openSupplierForm() {
        SupplierForm supplierForm = new SupplierForm(this, supplierController);
        JOptionPane.showMessageDialog(this, supplierForm, "Add Supplier", JOptionPane.PLAIN_MESSAGE);
        refreshSuppliers();
    }


    protected void refreshSuppliers() {
        supplierTableModel.setRowCount(0);
        try {
            List<Supplier> suppliers = supplierController.getAllSuppliers();
            for (Supplier supplier : suppliers) {
                Object[] row = { supplier.getId(), supplier.getName()};
                supplierTableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
