package controllers;

import dao.impl.StorageDAOImpl;
import dao.interfaces.StorageDAO;
import entities.Product;

public class StorageController {
    private StorageDAO storageDAO;

    public StorageController() {
        storageDAO = new StorageDAOImpl();
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
