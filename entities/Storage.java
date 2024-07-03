package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.impl.ProductDAOImpl;
import dao.impl.StockMovementDAOImpl;
import entities.enums.MovementType;

public class Storage {
    private static Storage instance;
    private final StockMovementDAOImpl stockMovements;
    private final ProductDAOImpl products;

    public Storage(StockMovementDAOImpl stockMovements, ProductDAOImpl products) {
        this.stockMovements = stockMovements;
        this.products = products;
    }

    public static Storage getInstance(StockMovementDAOImpl stockMovements,  ProductDAOImpl products) {
        if (instance == null) {
            instance = new Storage(stockMovements, products);
        }
        return instance;
    }
    public void addProduct(Product product, Double quantity) throws Exception {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product must not be null and quantity must be positive");
        }
        stockMovements.registerMovement(new StockMovement(0, product, MovementType.ADJUSTMENT, quantity, LocalDate.now()));
        products.registerObject(product);
    }

    public void removeProduct(Product product, Double quantity) throws Exception {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product must not be null and quantity must be positive");
        }
        else if (products.searchObjectById(product.getId()) != null) {
            throw new IllegalArgumentException("Product not found in stock");
        }
        stockMovements.registerMovement(new StockMovement(0,  product, MovementType.ADJUSTMENT, -quantity, LocalDate.now()));
        products.removeObject(product);
    }

    public int checkProductQuantity(Product product) throws Exception {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        int quantity = 0;
        for (StockMovement movement : stockMovements.listMovements()) {
            if (movement.getProduct().equals(product)) {
                if(movement.getType() == MovementType.ADJUSTMENT){
                    quantity = movement.getQuantity().intValue();
                } else if(movement.getType() == MovementType.PURCHASE){
                    quantity += movement.getQuantity();
                } else {
                    quantity -= movement.getQuantity();
                }
            }
        }
        return quantity;
    }

    public ProductDAOImpl getProducts(){
        return products;
    }
}
