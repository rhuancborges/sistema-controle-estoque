package dao.interfaces;

import entities.Product;

public interface StorageDAO {
	void addProduct(Product product, Double quantity) throws Exception;
	void removeProduct(Product product, Double quantity) throws Exception;
    int checkProductQuantity(Product product) throws Exception;
}
