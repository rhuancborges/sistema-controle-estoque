package entities;

import java.time.LocalDate;
import entities.enums.MovementType;

public class StockMovement {
    private LocalDate date;
    private Product product;
    private MovementType type;
    private Double quantity;
    private Supplier supplier;

    public StockMovement() {
    }
    
    public StockMovement(LocalDate date, Product product, MovementType type, Double quantity, Supplier supplier) {
        this.date = date;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public StockMovement(LocalDate date, Product product, MovementType type, Double quantity) {
        this.setDate(date);
        this.product = product;
        this.type = type;
        this.quantity = quantity;
    }

    public StockMovement(LocalDate date, String name, MovementType type, Double quantity, Product product) {
        this.setDate(date);
    	this.product = product;
        this.type = type;
        this.quantity = quantity;
	}

	public Product getProduct() {
        return product;
    }

    public MovementType getType() {
        return type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
