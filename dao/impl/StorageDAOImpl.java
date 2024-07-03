package dao.impl;

import dao.interfaces.StorageDAO;
import entities.Product;
import entities.Storage;

public class StorageDAOImpl implements StorageDAO {
    private Storage stock;

    public StorageDAOImpl(StockMovementDAOImpl stockMovements, ProductDAOImpl products) {
        this.stock = Storage.getInstance(stockMovements, products);
    }

    @Override
    public void addProduct(Product product, Double quantity) throws Exception {
        try {
            stock.addProduct(product, quantity);
        } catch (Exception e) {
            throw new Exception("Error adding product to storage", e);
        }
    }

    @Override
    public void removeProduct(Product product, Double quantity) throws Exception {
        try {
            stock.removeProduct(product, quantity);
        } catch (Exception e) {
            throw new Exception("Error removing product from storage", e);
        }
    }

    @Override
    public int checkProductQuantity(Product product) throws Exception {
        try {
            return stock.checkProductQuantity(product);
        } catch (Exception e) {
            throw new Exception("Error checking product quantity", e);
        }
    }
}
