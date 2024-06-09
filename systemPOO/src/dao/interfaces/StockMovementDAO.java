package dao.interfaces;

import java.util.List;

import entities.Product;
import entities.StockMovement;
import entities.Supplier;
import entities.enums.MovementType;

public interface StockMovementDAO {
    void registerMovement(StockMovement movement) throws Exception;
    List<StockMovement> listMovements() throws Exception;
    List<StockMovement> listMovementsByType(MovementType type) throws Exception;
    List<StockMovement> listMovementsByProduct(Product product) throws Exception;
    List<StockMovement> listMovementsBySupplier(Supplier supplier) throws Exception;
}
