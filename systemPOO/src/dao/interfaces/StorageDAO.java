package dao.interfaces;

import entities.Product;

public interface StorageDAO {
	    void addProduct(Product product, int quantity) throws Exception;
	    void removeProduct(Product product, int quantity) throws Exception;
	    int checkProductQuantity(Product product) throws Exception;
	}