package service;

import java.util.List;

import dto.ProductDTO;
import dto.UserDTO;


public interface ProductService {
	
	public void addProduct(ProductDTO product, UserDTO user);
	public void deleteProductById(ProductDTO product, UserDTO user);
	public void updateProduct(ProductDTO product, UserDTO user) ;
	public ProductDTO findProductById(int id);
	public List<ProductDTO> findAllProducts();
	public boolean productIdIsUsed(ProductDTO product);
}
