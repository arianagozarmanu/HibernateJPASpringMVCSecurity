package converter;

import dto.ProductDTO;
import dto.UserDTO;
import model.Product;
import model.User;

public class DtoToPersistentConversion {

	public static Product convertProductDtoToPersistentProduct(ProductDTO product) {
		Product persistenProduct = new Product();
		persistenProduct.setIdproduct(product.getIdproduct());
		persistenProduct.setName(product.getName());
		persistenProduct.setPrice(product.getPrice());
		User user = convertUserDtoToPersistentUser(product.getUser());
		persistenProduct.setUser(user);
		return persistenProduct;
	}

	public static User convertUserDtoToPersistentUser(UserDTO user) {
		User persistentUser = new User();
		persistentUser.setIduders(user.getIduders());
		persistentUser.setUsername(user.getUsername());
		persistentUser.setPassword(user.getPassword());
		persistentUser.setEnabled(user.isEnabled());
		persistentUser.setEmail(user.getEmail());
		persistentUser.setAge(user.getAge());
		persistentUser.setLastOperationDate(user.getLastOperationDate());
		return persistentUser;
	}
}
