package entities;

import dao.impl.ProductDAOImpl;
import dao.impl.StockMovementDAOImpl;

import java.util.Objects;

public class Product {
    private Integer id;
    private String barCode;
    private String name;
    private Double price;

    private Supplier supplier;

    public Product() {}

    public Product(String name) {
        this.name = name;
    }

    public Product(Integer id){
        this.id = id;
    }
    
    public Product(Integer id, String barCode, String name, Double price) {
        this.id = id;
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    public Product(String name, Double price) {
    	this.name = name;
    	this.price = price;
    }

    public void setSupplier(Supplier supplier, Product product) {
        product.supplier = supplier;
    }

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", barCode=" + barCode + ", name=" + name + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return Objects.equals(id, other.id);
    }

	public Double getQuantity() throws Exception{
		return (double) Storage.getInstance(new StockMovementDAOImpl(), new ProductDAOImpl()).checkProductQuantity(this);
        // A criação de instâncias de StockMovementDAOImpl e ProductDAOImpl dentro de getinstance() é apenas para não gerar
        // erro de compilação, uma vez que a instância ÚNICA de Storage é criada na linha nº 61 da classe MainView
	}

	public Supplier getSupplier() {
		return supplier;
	}
}
