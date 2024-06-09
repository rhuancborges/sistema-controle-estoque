package application;

import controllers.ProductController;
import entities.Product;

public class Main {
    public static void main(String[] args) {
        try {
            // Instanciar o controlador de Product
            ProductController productController = new ProductController();

            // Criar alguns produtos de teste
            Product product1 = new Product(1, "001", "Product A", 10.0);
            Product product2 = new Product(2, "002", "Product B", 20.0);
            Product product3 = new Product(3, "003", "Product C", 30.0);

            // Registrar os produtos no DAO
            productController.registerProduct(product1);
            productController.registerProduct(product2);
            productController.registerProduct(product3);

            // Listar todos os produtos
            System.out.println("All products:");
            for (Product product : productController.getAllProducts()) {
                System.out.println(product);
            }

            // Buscar produto por ID
            System.out.println("\nSearching for product with ID 2:");
            Product searchedProductById = productController.getProductById(2);
            System.out.println(searchedProductById != null ? searchedProductById : "Product not found");

            // Buscar produto por BarCode
            System.out.println("\nSearching for product with BarCode '003':");
            Product searchedProductByBarCode = productController.getProductByBarCode("003");
            System.out.println(searchedProductByBarCode != null ? searchedProductByBarCode : "Product not found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
