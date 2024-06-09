package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.ObjectDAO;
import entities.Supplier;

public class SupplierDAOImpl implements ObjectDAO<Supplier> {
    private List<Supplier> suppliers;
    
    public SupplierDAOImpl() {
        suppliers = new ArrayList<>();
    }
    
    @Override
    public void registerObject(Supplier supplier) throws Exception {
        if (supplier != null && supplier.getId() != null) {
            suppliers.add(supplier);
        } else {
            throw new Exception("Supplier or Supplier ID is null");
        }
    }

    @Override
    public Supplier searchObjectById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("ID cannot be null");
        }
        for (Supplier supplier : suppliers) {
            if (supplier.getId().equals(id)) {
                return supplier;
            }
        }
        return null;
    }

    @Override
    public Supplier searchObjectByString(String cnpj) throws Exception {
        if (cnpj == null) {
            throw new Exception("CNPJ cannot be null");
        }
        for (Supplier supplier : suppliers) {
            if (supplier.getCnpj().equals(cnpj)) {
                return supplier;
            }
        }
        return null;
    }

    @Override
    public List<Supplier> listObject() throws Exception {
        return new ArrayList<>(suppliers);
    }
}
