package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.DtoToPersistentConversion;
import converter.PersistentToDtoConversion;
import dao.*;
import dto.ProductDTO;
import dto.UserDTO;
import model.*;
import util.*;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDaoImpl;
	
	@Autowired 
	UserDao userDaoImpl;
	
	@Transactional(rollbackFor = Exception.class)
	public void addProduct(ProductDTO product, UserDTO user) {
		userDaoImpl.insertLastActionDate(serviceUtils.getCurrentDate(), user.getIduders());
		Product persistentProduct=DtoToPersistentConversion.convertProductDtoToPersistentProduct(product);
		productDaoImpl.persist(persistentProduct);		
	}


	@Transactional
	public void deleteProductById(ProductDTO product, UserDTO user) {
		// TODO Auto-generated method stub
		userDaoImpl.insertLastActionDate(serviceUtils.getCurrentDate(), user.getIduders());
		Product persistentProduct=DtoToPersistentConversion.convertProductDtoToPersistentProduct(product);
		productDaoImpl.deleteById(persistentProduct);
	}


	@Transactional
	public void updateProduct(ProductDTO product, UserDTO user) {
		// TODO Auto-generated method stub
		userDaoImpl.insertLastActionDate(serviceUtils.getCurrentDate(), user.getIduders());
		Product persistentProduct=DtoToPersistentConversion.convertProductDtoToPersistentProduct(product);
		productDaoImpl.update(persistentProduct);		
	}

	@Transactional(readOnly = true)
	public ProductDTO findProductById(int id) {
		Product persistentProduct = productDaoImpl.findById(id);
		ProductDTO product = PersistentToDtoConversion.convertProductToProductDTO(persistentProduct);
		return product;
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAllProducts() {
		// TODO Auto-generated method stub
		List<Product> persistentProducts=productDaoImpl.list();
		List<ProductDTO> products=new ArrayList<ProductDTO>();
		for(Product prod:persistentProducts){
			ProductDTO product=PersistentToDtoConversion.convertProductToProductDTO(prod);
			products.add(product);
		}
		return products;
	}

	@Transactional(readOnly = true)
	public boolean productIdIsUsed(ProductDTO product) {
			if(productDaoImpl.findById(product.getIdproduct())==null)
					return false;
			else return true;
	}


}
