package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mapper.ProductRowMapper;
import model.Product;

@Repository(value = "productDaoImpl")
@Transactional
public class ProductDaoImpl implements ProductDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
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
		Query query = getSession().createQuery("UPDATE products SET name= ");
		query.setParameter("stockName", "DIALOG1");
		query.setParameter("stockCode", "7277");
		int result = query.executeUpdate();
		String sql = "UPDATE products SET name=?, price=?, iduser=? WHERE idproduct=?";

		int rows = jdbcTemplate.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getIduser(), product.getIdproduct() });
		System.out.println(rows + " row(s) updated in Product Table.");

	}

	@SuppressWarnings({ "unchecked" })
	public Product findById(int id) {
		String sql = "SELECT * FROM products WHERE idproduct=?";

		Product product = (Product) jdbcTemplate.queryForObject(sql, new Object[] { id }, new ProductRowMapper());

		return product;
	}

	public List<Product> findByUserId(int id) {
		String sql = "SELECT * FROM products";

		List<Product> products = new ArrayList<Product>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			Product prod = new Product();
			prod.setIdproduct(Integer.parseInt(String.valueOf(row.get("idproduct"))));
			prod.setName((String) row.get("name"));
			prod.setPrice(Double.parseDouble(String.valueOf(row.get("price"))));
			prod.setIduser(Integer.parseInt(String.valueOf(row.get("iduser"))));
			if (prod.getIduser() == id)
				products.add(prod);
		}

		return products;
	}

	@SuppressWarnings("unchecked")
	public List<Product> list() {
		List<Product> listProducts = (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listProducts;
	}

}
