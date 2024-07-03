package views.login;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import controllers.*;
import dao.impl.ProductDAOImpl;
import dao.impl.StockMovementDAOImpl;
import dao.impl.SupplierDAOImpl;
import dao.impl.UserDAOImpl;
import entities.Storage;
import views.product.ProductView;
import views.stockmovement.StockMovementView;
import views.supplier.SupplierView;

public class MainView extends JFrame {
    private static final long serialVersionUID = 1L;
    private UserController userController;
    private ProductController productController;
    private StockMovementController stockMovementController;
    private SupplierController supplierController;

    public MainView(UserController userController, ProductController productController, 
                    StockMovementController stockMovementController, SupplierController supplierController) {
        this.userController = userController;
        this.productController = productController;
        this.stockMovementController = stockMovementController;
        this.supplierController = supplierController;
        initComponents();
    }

    private void initComponents() {
        setTitle("Main View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        StockMovementView stockMovementView = new StockMovementView(stockMovementController);
        tabbedPane.addTab("Products", new ProductView(productController, stockMovementView));
        tabbedPane.addTab("Stock Movements", stockMovementView);
        tabbedPane.addTab("Suppliers", new SupplierView(supplierController));
        tabbedPane.addTab("Users", new UserView(userController));
        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StockMovementDAOImpl stockMovementDAO = new StockMovementDAOImpl();
                ProductDAOImpl productDAO = new ProductDAOImpl();
                UserController userController = new UserController(new UserDAOImpl());
                ProductController productController = new ProductController(productDAO);
                StockMovementController stockMovementController = new StockMovementController(stockMovementDAO);
                SupplierController supplierController = new SupplierController(new SupplierDAOImpl());
                StorageController storageController = new StorageController(stockMovementDAO, productDAO);
                new MainView(userController, productController, stockMovementController, supplierController).setVisible(true);
            }
        });
    }
}
