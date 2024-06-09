package controllers;

import java.util.List;
import dao.interfaces.ObjectDAO;
import entities.Product;

public class ProductController {
    private ObjectDAO<Product> productDAO;
    
    public ProductController() {
    }
    
    public ProductController(ObjectDAO<Product> productDAO) {
        if (productDAO == null) {
            throw new IllegalArgumentException("ProductDAO cannot be null");
        }
        this.productDAO = productDAO;
    }

    public void registerProduct(Product product) throws Exception {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (product.getBarCode() == null || product.getBarCode().isEmpty()) {
            throw new IllegalArgumentException("Barcode cannot be null or empty");
        }
        // Verificar se o ID é único antes de registrar o produto
        if (isProductIdUnique(product.getId())) {
            productDAO.registerObject(product);
        } else {
            throw new IllegalArgumentException("ID must be unique");
        }
    }

    public double getProductStockQuantity(int productId) throws Exception {
        Product product = getProductById(productId);
        return (product != null) ? product.getQuantity() : 0;
    }

    
    private boolean isProductIdUnique(Integer id) throws Exception {
        List<Product> existingProducts = productDAO.listObject();
        for (Product existingProduct : existingProducts) {
            if (existingProduct.getId().equals(id)) {
                return false; // ID já existe, não é único
            }
        }
        return true; // ID é único
    }


    public Product getProductById(Integer id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return productDAO.searchObjectById(id);
    }

    public List<Product> getAllProducts() throws Exception {
        return productDAO.listObject();
    }

    public Product getProductByBarCode(String barCode) throws Exception {
        if (barCode == null) {
            throw new IllegalArgumentException("Bar code cannot be null");
        }
        return productDAO.searchObjectByString(barCode);
    }
}
