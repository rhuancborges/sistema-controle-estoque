package controllers;

import java.util.List;
import dao.interfaces.ObjectDAO;
import entities.Product;

public class ProductController {
    private ObjectDAO<Product> productDAO;
    
    public ProductController() {
    }

    public ProductController(ObjectDAO<Product> productDAO) {
        this.productDAO = productDAO;
    }

    public void registerProduct(Product product) throws Exception {
        productDAO.registerObject(product);
    }

    public Product getProductById(Integer id) throws Exception {
        return productDAO.searchObjectById(id);
    }

    public List<Product> getAllProducts() throws Exception {
        return productDAO.listObject();
    }

    public Product getProductByBarCode(String barCode) throws Exception {
        return productDAO.searchObjectByString(barCode);
    }
}
