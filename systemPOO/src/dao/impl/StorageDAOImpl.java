package dao.impl;

import dao.interfaces.StorageDAO;
import entities.Product;
import entities.Storage;

public class StorageDAOImpl implements StorageDAO {
    private Storage stock;

    public StorageDAOImpl() {
        this.stock = new Storage();
    }

    @Override
    public void addProduct(Product product, int quantity) {
        stock.addProduct(product, quantity);
    }

    @Override
    public void removeProduct(Product product, int quantity) {
        stock.removeProduct(product, quantity);
    }

    @Override
    public int checkProductQuantity(Product product) {
        return stock.checkProductQuantity(product);
    }
}
