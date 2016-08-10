package service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.*;
import dto.*;
import model.*;
import repository.*;
import util.*;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional(rollbackFor = Exception.class)
	public void addProduct(ProductDTO product, UserDTO user) {
		user.setLastOperationDate(serviceUtils.getCurrentDate());
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.save(persistentUser);
		Product persistentProduct=DtoToPersistentConversion.convertProductDtoToPersistentProduct(product);
		productRepo.save(persistentProduct);		
	}


	@Transactional
	public void deleteProductById(ProductDTO product, UserDTO user) {
		user.setLastOperationDate(serviceUtils.getCurrentDate());
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.save(persistentUser);
		Product persistentProduct=DtoToPersistentConversion.convertProductDtoToPersistentProduct(product);
		productRepo.delete(persistentProduct);
	}


	@Transactional
	public void updateProduct(ProductDTO product, UserDTO user) {
		user.setLastOperationDate(serviceUtils.getCurrentDate());
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.save(persistentUser);
		Product persistentProduct=DtoToPersistentConversion.convertProductDtoToPersistentProduct(product);
		productRepo.save(persistentProduct);		
	}

	@Transactional(readOnly = true)
	public ProductDTO findProductById(int id) {
		Product persistentProduct = productRepo.findById(id);
		ProductDTO product = PersistentToDtoConversion.convertProductToProductDTO(persistentProduct);
		return product;
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAllProducts() {
		List<Product> persistentProducts=productRepo.findAll();
		List<ProductDTO> products=new ArrayList<ProductDTO>();
		for(Product prod:persistentProducts){
			ProductDTO product=PersistentToDtoConversion.convertProductToProductDTO(prod);
			products.add(product);
		}
		return products;
	}

	@Transactional(readOnly = true)
	public boolean productIdIsUsed(ProductDTO product) {
			if(productRepo.findById(product.getIdproduct())==null)
					return false;
			else return true;
	}


}
