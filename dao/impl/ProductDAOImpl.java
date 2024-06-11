package dao.impl;

import java.util.ArrayList;
import java.util.List;
import dao.interfaces.ObjectDAO;
import entities.Product;

public class ProductDAOImpl implements ObjectDAO<Product> {
    private List<Product> products;

    public ProductDAOImpl() {
        products = new ArrayList<>();
    }

    @Override
    public void registerObject(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
        System.out.println("Product added: " + product.getName());
    }

    @Override
    public Product searchObjectById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        for (Product product : products) {
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product searchObjectByString(String searchString) {
        if (searchString == null) {
            throw new IllegalArgumentException("Search string cannot be null");
        }
        for (Product product : products) {
            if (searchString.equals(product.getBarCode())) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> listObject() {
        System.out.println("Listing products: " + products.size() + " products found");
        return new ArrayList<>(products);
    }
}
