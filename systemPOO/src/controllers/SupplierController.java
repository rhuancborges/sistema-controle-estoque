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

    public void registerSupplier(Supplier supplier) throws Exception {
        supplierDAO.registerObject(supplier);
    }

    public Supplier getSupplierById(Integer id) throws Exception {
        return supplierDAO.searchObjectById(id);
    }

    public List<Supplier> getAllSuppliers() throws Exception {
        return supplierDAO.listObject();
    }

    public Supplier getSupplierByCnpj(String cnpj) throws Exception {
        return supplierDAO.searchObjectByString(cnpj);
    }
}
