package dao.impl;

import java.util.ArrayList;
import java.util.List;
import dao.interfaces.StockMovementDAO;
import entities.Product;
import entities.StockMovement;
import entities.enums.MovementType;

public class StockMovementDAOImpl implements StockMovementDAO {
    private List<StockMovement> movements;

    public StockMovementDAOImpl() {
        movements = new ArrayList<>();
    }

    @Override
    public void registerMovement(StockMovement movement) throws Exception {
        if (movement == null) {
            throw new IllegalArgumentException("Movement cannot be null");
        }
        try {
            movements.add(movement);
        } catch (Exception e) {
            throw new Exception("Error registering stock movement", e);
        }
    }

    @Override
    public List<StockMovement> listMovements() throws Exception {
        try {
            return new ArrayList<>(movements);
        } catch (Exception e) {
            throw new Exception("Error listing stock movements", e);
        }
    }

    @Override
    public List<StockMovement> listMovementsByType(MovementType type) throws Exception {
        try {
            List<StockMovement> result = new ArrayList<>();
            for (StockMovement movement : movements) {
                if (movement.getType() == type) {
                    result.add(movement);
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error listing stock movements by type", e);
        }
    }

    @Override
    public List<StockMovement> listMovementsByProduct(Product product) throws Exception {
        try {
            List<StockMovement> result = new ArrayList<>();
            for (StockMovement movement : movements) {
                if (movement.getProduct().equals(product)) {
                    result.add(movement);
                }
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error listing stock movements by product", e);
        }
    }

}
