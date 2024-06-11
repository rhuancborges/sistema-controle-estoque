package entities;

import java.util.Objects;

public class Supplier {
    private Integer id;
    private String cnpj;
    private String name;

    public Supplier() {
    }
    
    public Supplier(Integer id, String cnpj, String name) {
        setId(id);
        setCnpj(cnpj);
        setName(name);
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new IllegalArgumentException("CNPJ cannot be null or empty");
        }
        this.cnpj = cnpj;
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

    public String getSupplier() {
        return "ID: " + id + ", CNPJ: " + cnpj + ", Name: " + name;
    }

    @Override
    public String toString() {
        return "Supplier [id=" + id + ", cnpj=" + cnpj + ", name=" + name + "]";
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Supplier other = (Supplier) obj;
        return Objects.equals(id, other.id);
    }
}
