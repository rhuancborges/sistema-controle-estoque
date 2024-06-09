package controllers;

import java.util.List;
import dao.impl.SupplierDAOImpl;
import dao.interfaces.ObjectDAO;
import entities.Supplier;

public class SupplierController {
    private ObjectDAO<Supplier> supplierDAO;

    public SupplierController() {
        supplierDAO = new SupplierDAOImpl();
    }

    public SupplierController(SupplierDAOImpl supplierDAOImpl) {
        this.supplierDAO = supplierDAOImpl;
    }

    public void registerSupplier(Supplier supplier) throws Exception {
        // Verificações de validade dos dados do fornecedor
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        if (supplier.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (supplier.getCnpj() == null || supplier.getCnpj().isEmpty()) {
            throw new IllegalArgumentException("CNPJ cannot be null or empty");
        }
        if (supplier.getName() == null || supplier.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        // Verificar se o ID é único antes de registrar o fornecedor
        if (isSupplierIdUnique(supplier.getId())) {
            supplierDAO.registerObject(supplier);
        } else {
            throw new IllegalArgumentException("ID must be unique");
        }
    }

    private boolean isSupplierIdUnique(Integer id) throws Exception {
        List<Supplier> existingSuppliers = supplierDAO.listObject();
        for (Supplier existingSupplier : existingSuppliers) {
            if (existingSupplier.getId().equals(id)) {
                return false; // ID já existe, não é único
            }
        }
        return true; // ID é único
    }

    public Supplier getSupplierById(Integer id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return supplierDAO.searchObjectById(id);
    }

    public List<Supplier> getAllSuppliers() throws Exception {
        return supplierDAO.listObject();
    }

    public Supplier getSupplierByCnpj(String cnpj) throws Exception {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ cannot be null");
        }
        return supplierDAO.searchObjectByString(cnpj);
    }
}
