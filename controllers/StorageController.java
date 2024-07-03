package controllers;

import dao.impl.ProductDAOImpl;
import dao.impl.StockMovementDAOImpl;
import dao.impl.StorageDAOImpl;
import dao.interfaces.StorageDAO;
import entities.Product;

public class StorageController {
    private StorageDAO storageDAO;

    public StorageController(StockMovementDAOImpl stockMovements, ProductDAOImpl products) {
        storageDAO = new StorageDAOImpl(stockMovements, products);
    }

    public void addProduct(Product product, Double quantity) throws Exception {
        storageDAO.addProduct(product, quantity);
    }

    public void removeProduct(Product product, Double quantity) throws Exception {
        storageDAO.removeProduct(product, quantity);
    }

    public int checkProductQuantity(Product product) throws Exception {
        return storageDAO.checkProductQuantity(product);
    }
}
