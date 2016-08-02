package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	
	public void save(Product product);
	public Product findById(int id);
	public List<Product> list();
	public void deleteById(Product product);
	public void update(Product product);
	public List<Product> findByUserId(int id);
}
