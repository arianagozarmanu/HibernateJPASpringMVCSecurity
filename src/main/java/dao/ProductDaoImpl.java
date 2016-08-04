package dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Product;

@Repository(value = "productDaoImpl")
@Transactional
public class ProductDaoImpl implements ProductDao {


	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
	}
	
	
	public void persist(Product product) {
		getSession().persist(product);
		System.out.println("Product " + product.getName() + " was added!");
	}

	public void deleteById(Product product) {
		getSession().delete(product);
		System.out.println("Product " + product.getName() + " was deleted!");
	}

	public void update(Product product) {
		getSession().update(product);
		System.out.println(product.getName() + " is updated in Product Table.");
	}

	public Product findById(int idproduct) {
			Product prod = (Product) getSession().get(Product.class, idproduct);
			System.out.println("Sunt in try");
			return prod;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByUserId(int iduser) {
		String sql = "SELECT p FROM Product p WHERE p.iduser=:iduser";
		Query query = getSession().createQuery(sql);
		query.setParameter("iduser", iduser);
		List<Product> products = (List<Product>)query.list();
		return products;
	}

	@SuppressWarnings("unchecked")
	public List<Product> list() {
		List<Product> listProducts = (List<Product>) getSession().createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listProducts;
	}

}
