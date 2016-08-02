package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idproduct", nullable=false)
	private int idproduct;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="price", nullable=false)
	private double price;
	@Column(name="iduser", nullable=false)
	private int iduser;
	
	public Product(){
	}
	
	public Product(int idprod, String name, double price, int iduser){
		this.idproduct=idprod;
		this.name=name;
		this.price=price;
		this.iduser=iduser;		
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
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
	
}
