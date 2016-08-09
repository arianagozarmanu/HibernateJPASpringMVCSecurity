package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idproduct", nullable = false)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name="iduser", nullable=false)
	private User user;

	public Product() {
	}

	public Product(int idprod, String name, double price, User user) {
		this.id = idprod;
		this.name = name;
		this.price = price;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIdproduct() {
		return id;
	}

	public void setIdproduct(int idproduct) {
		this.id = idproduct;
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
