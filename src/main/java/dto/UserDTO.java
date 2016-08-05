package dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int iduders;
	private String username;
	private String password;
	private int enabled;
	private String email;
	private int age;
	private java.util.Date lastOperationDate; 
	
	private List<ProductDTO> products = new ArrayList<ProductDTO>(0);
	
	public UserDTO(){
	}
	
	public UserDTO(int iduders, String username, String password, int enabled, String email, int age, java.util.Date date) {
		this.iduders = iduders;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.age = age;
		this.lastOperationDate=date;
	}
	
    public List<ProductDTO> getProducts() {
        return products;
    }
    
	public void setProducts(List<ProductDTO> products){
		this.products=products;
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
