package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	
	public void add(Product product);
	public Product findById(int idproduct);
	public List<Product> findAll();
	public void deleteById(Product product);
	public void update(Product product);
	public List<Product> findByUserId(int id);
}
