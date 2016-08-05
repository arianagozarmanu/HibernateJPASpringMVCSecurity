package converter;

import java.util.ArrayList;
import java.util.List;

import dto.ProductDTO;
import dto.UserDTO;
import model.Product;
import model.User;

public final class PersistentToDtoConversion {

	public static ProductDTO convertProductToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setIdproduct(product.getIdproduct());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		UserDTO user = convertUserToUserDTO(product.getUser());
		productDTO.setUser(user);
		return productDTO;
	}

	public static UserDTO convertUserToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setIduders(user.getIduders());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setEnabled(user.isEnabled());
		userDTO.setEmail(user.getEmail());
		userDTO.setAge(user.getAge());
		userDTO.setLastOperationDate(user.getLastOperationDate());
		
		if(user.getProducts()!=null){
			List<Product> products = user.getProducts();
			List<ProductDTO> dtoProducts = new ArrayList<ProductDTO>();
			for(Product prod:products){
				ProductDTO dtoProd = convertProductToProductDTOprivate(prod);
				dtoProducts.add(dtoProd);
			}
			userDTO.setProducts(dtoProducts);
		}
		return userDTO;
	}
	
	//daca as fi apelat convertProductToProductDTO ar fi intrat in bucla infinita
	private static  ProductDTO convertProductToProductDTOprivate(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setIdproduct(product.getIdproduct());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		return productDTO;
	}

}
