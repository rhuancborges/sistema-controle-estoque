package entities;

public class Supplier {
	private Integer id;
	private String cnpj;
	private String name;
	
	
	public Supplier() {
	}

	public Supplier(Integer id, String cnpj, String name) {
		super();
		setId(id);
		setCnpj(cnpj);
		setName(name);
	}
	
	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
