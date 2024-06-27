package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.enums.MovementType;

public class Storage {
    private static Storage instance;
    private List<StockMovement> stockMovements;
    private List<Product> products;

    public Storage() {
        this.stockMovements = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    public void addProduct(Product product, Double quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product must not be null and quantity must be positive");
        }
        stockMovements.add(new StockMovement(0, product, MovementType.ADJUSTMENT, quantity, LocalDate.now()));
        products.add(product);
    }

    public void removeProduct(Product product, Double quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product must not be null and quantity must be positive");
        }
        else if (!products.contains(product)) {
            throw new IllegalArgumentException("Product not found in stock");
        }
        stockMovements.add(new StockMovement(0,  product, MovementType.ADJUSTMENT, -quantity, LocalDate.now()));
        products.remove(product);
    }

    public int checkProductQuantity(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        int quantity = 0;
        for (StockMovement movement : stockMovements) {
            if (movement.getProduct().equals(product) && movement.getType() == MovementType.ADJUSTMENT) {
                quantity += movement.getQuantity();
            }
        }
        return quantity;
    }
}
