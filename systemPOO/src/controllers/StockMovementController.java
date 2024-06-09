package controllers;

import java.time.LocalDate;
import java.util.List;

import dao.interfaces.StockMovementDAO;
import entities.Product;
import entities.StockMovement;
import entities.Supplier;
import entities.enums.MovementType;

public class StockMovementController {
    private StockMovementDAO stockMovementDAO;

    public StockMovementController() {
    }
    
    public StockMovementController(StockMovementDAO stockMovementDAO) {
        this.stockMovementDAO = stockMovementDAO;
    }

    public void registerMovement(StockMovement movement) throws Exception {
        try {
            stockMovementDAO.registerMovement(movement);
        } catch (Exception e) {
            throw new Exception("Failed to register stock movement: " + e.getMessage());
        }
    }

    public List<StockMovement> getAllMovements() throws Exception {
        try {
            return stockMovementDAO.listMovements();
        } catch (Exception e) {
            throw new Exception("Failed to retrieve stock movements: " + e.getMessage());
        }
    }


    public List<StockMovement> getMovementsByType(MovementType type) throws Exception {
        try {
            return stockMovementDAO.listMovementsByType(type);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve stock movements by type: " + e.getMessage());
        }
    }

    public List<StockMovement> getMovementsByProduct(Product product) throws Exception {
        try {
            return stockMovementDAO.listMovementsByProduct(product);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve stock movements by product: " + e.getMessage());
        }
    }

    public List<StockMovement> getMovementsBySupplier(Supplier supplier) throws Exception {
        try {
            return stockMovementDAO.listMovementsBySupplier(supplier);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve stock movements by supplier: " + e.getMessage());
        }
    }

    public void registerMovement(Product product, MovementType type, Double quantity, LocalDate date, Supplier supplier) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        StockMovement movement = new StockMovement(date, product, type, quantity, supplier);
        try {
            stockMovementDAO.registerMovement(movement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
