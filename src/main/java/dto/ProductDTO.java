package dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idproduct;
	private String name;
	private double price;
	private UserDTO user=new UserDTO();

	public ProductDTO() {
	}

	public ProductDTO(int idprod, String name, double price, UserDTO user) {
		this.idproduct = idprod;
		this.name = name;
		this.price = price;
		this.user = user;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
