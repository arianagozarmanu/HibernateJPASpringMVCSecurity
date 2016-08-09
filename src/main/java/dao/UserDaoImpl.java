package dao;

import java.util.List;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.User;

@Repository(value = "userDaoImpl")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
	}

	public void add(User user) {
		getSession().persist(user);
		System.out.println("Product " + user.getUsername() + " was added!");
	}
	

	public void addRole(String username, String role) {
		String sql = "INSERT INTO user_roles(username, role) VALUES (:username,:role)";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("username", username);
		query.setParameter("role", role);
		query.executeUpdate();
		

		System.out.println("User with username=" + username + " was insterted with ROLE_USER");
	}

	public User findById(int iduders) {
		return (User) getSession().get(User.class, iduders);
	}

	public User findByName(String name) {
		String sql = "SELECT p FROM User p WHERE p.username=:username";
		Query query = getSession().createQuery(sql);
		query.setParameter("username", name);
		return (User) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> listUsers = (List<User>) getSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUsers;
	}

	public void deleteById(int id) {
		Query query = getSession().createQuery("delete from User where idusers = :idusers");
		query.setParameter("idusers", id);
		int rows = query.executeUpdate();
		System.out.println(rows + " row(s) deleted.");

	}

	public void update(int iduders, String username, String password, int enabled, String email, int age) {
		String sql = "UPDATE User SET username=:username, password=:pass, enabled=:en, email=:email, age=:age WHERE iduders=:idusers";
		Query query = getSession().createQuery(sql);
		query.setParameter("username", username);
		query.setParameter("pass", password);
		query.setParameter("en", enabled);
		query.setParameter("email", email);
		query.setParameter("age", age);
		query.setParameter("idusers", iduders);

		int rows = query.executeUpdate();
		System.out.println(rows + " row(s) updated in User Table.");

	}

	public void insertLastActionDate(java.util.Date date, int id) {
		String sql = "UPDATE User SET lastOperationDate=:date WHERE iduders=:iduser";
		Query query = getSession().createQuery(sql);
		query.setParameter("date", date);
		query.setParameter("iduser", id);
		int rows = query.executeUpdate();
		System.out.println(rows + " row(s) updated with new Last Action Date.");
	}

}
