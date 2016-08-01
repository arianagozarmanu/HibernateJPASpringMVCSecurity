package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import dao.*;
import model.*;
import util.*;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDaoImpl;

	@Autowired
	UserDao userDaoImpl;

	@Transactional(rollbackFor = Exception.class)
	public void addProduct(Product product, User user) {
		userDaoImpl.insertLastActionDate(serviceUtils.getCurrentDate(), user.getIduders());
		productDaoImpl.add(product);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProductById(Product product, User user) {
		userDaoImpl.insertLastActionDate(serviceUtils.getCurrentDate(), user.getIduders());
		productDaoImpl.deleteById(product);
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateProduct(Product product, User user) {
		// TODO Auto-generated method stub
		userDaoImpl.insertLastActionDate(serviceUtils.getCurrentDate(), user.getIduders());
		productDaoImpl.update(product);
	}

	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productDaoImpl.findById(id);
	}

	public List<Product> findProductsByUserId(int id) {
		// TODO Auto-generated method stub
		return productDaoImpl.findByUserId(id);
	}

	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return productDaoImpl.findAll();
	}

	public boolean productIdIsUsed(Product product) {
		try {
			Product prod = productDaoImpl.findById(product.getIdproduct());
			System.out.println("Produsul este " + prod.getName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
