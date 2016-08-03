package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idusers", nullable=false)
	private int iduders;
	@Column(name="username", nullable=false)
	private String username;
	@Column(name="password", nullable=false)
	private String password;
	@Column(name="enabled", nullable=false)
	private int enabled;
	@Column(name="email", nullable=false)
	private String email;
	@Column(name="age", nullable=false)
	private int age;
	@Column(name="lastOperationDate", nullable=true)
	private java.util.Date lastOperationDate; 
	
	@OneToMany(mappedBy="user")
	private List<Product> products;
	
	public User(){
	}
	
	public User(int iduders, String username, String password, int enabled, String email, int age, java.util.Date date) {
		this.iduders = iduders;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.age = age;
		this.lastOperationDate=date;
	}
	
    public List<Product> getProducts() {
        return products;
    }
 
    public void setAccounts(List<Product> products) {
        this.products = products;
    }
	public java.util.Date getLastOperationDate() {
		return lastOperationDate;
	}

	public void setLastOperationDate(java.util.Date lastOperationDate) {
		this.lastOperationDate = lastOperationDate;
	}

	public int getIduders() {
		return iduders;
	}
	public void setIduders(int iduders) {
		this.iduders = iduders;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int isEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
