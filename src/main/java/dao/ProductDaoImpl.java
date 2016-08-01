package dao;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.Product;

@Repository(value="productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {	
	}
	
	public void add(Product product) {
		entityManager.persist(product);
	}

	public void deleteById(Product product) {
		
		int rows = entityManager
				.createNativeQuery("DELETE FROM products WHERE idproduct='"+product.getIdproduct()+"'")
				.executeUpdate();
		System.out.println(rows + " row(s) deleted in Product Table.");
	}

	public void update(Product product) {
		
		int rows = entityManager
				.createNativeQuery(
						"UPDATE products SET name='" + product.getName() + "', price='" + product.getPrice() + "', iduser='" + product.getIduser()
								+ "' WHERE idproduct='" + product.getIdproduct() + "'")
				.executeUpdate();

		System.out.println(rows + " row(s) updated in Product Table.");

	}


	public Product findById(int  idproduct) {

		Product product = entityManager.find(Product.class,  idproduct);

		return product;
	}

	public List<Product> findByUserId(int id) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = builder.createQuery(Product.class);
		Root<Product> root = cq.from(Product.class);
		cq.select(root);
		cq.where(builder.equal(root.get("iduser"),id));
		
		try{
			return entityManager.createQuery(cq).getResultList();
		} catch(NoResultException e){
			return null;
		}

	}

	public List<Product> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Product> cq = builder.createQuery(Product.class);
	    Root<Product> root = cq.from(Product.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	  }


}
