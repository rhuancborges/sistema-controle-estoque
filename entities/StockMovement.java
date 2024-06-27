package entities;

import java.time.LocalDate;
import entities.enums.MovementType;

public class StockMovement {
    private Integer id;
    private LocalDate date;
    private Product product;
    private MovementType type;
    private Double quantity;

    public StockMovement() {
    }
    
    public StockMovement(Integer id, Product product, MovementType type, Double quantity, LocalDate date) {
        this.id = id;
        this.date = date;
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

    public Integer getId(){ return id; }
	public Product getProduct() {
        return product;
    }

    public MovementType getType() {
        return type;
    }

    public Double getQuantity() {
        return quantity;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
