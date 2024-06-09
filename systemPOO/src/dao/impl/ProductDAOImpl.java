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
        products.add(product);
        System.out.println("Product added: " + product.getName());
    }

    @Override
    public Product searchObjectById(Integer id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product searchObjectByString(String barCode) {
        for (Product product : products) {
            if (product.getBarCode().equals(barCode)) {
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
