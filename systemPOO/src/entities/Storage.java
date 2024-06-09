package entities;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Product, Integer> stock;

    public Storage() {
        this.stock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        stock.merge(product, quantity, Integer::sum);
    }

    public void removeProduct(Product product, int quantity) {
        if (stock.containsKey(product)) {
            int currQuantity = stock.get(product);
            if (currQuantity > quantity) {
                stock.put(product, currQuantity - quantity);
            } else {
                stock.remove(product);
            }
        }
    }

    public int checkProductQuantity(Product product) {
        return stock.getOrDefault(product, 0);
    }
}
