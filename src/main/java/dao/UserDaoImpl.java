package dao;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import model.User;

@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	}

	public void add(User user) {
		entityManager.persist(user);
	}

	public void addRole(String username, String role) {
		
		Query q = entityManager
				.createNativeQuery("INSERT INTO user_roles(username, role ) VALUES (?,?)");
		q.setParameter(1, username);
		q.setParameter(2, role);
		q.executeUpdate();

		System.out.println("User with username=" + username + " was insterted with ROLE_USER");
	}

	public User findById(int id) {
		User user = entityManager.find(User.class, id);
		return user;
	
	}
	
	public User findByName(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(builder.equal(root.get("username"),name));
		
		Object result = null;
		
		try{
			result = entityManager.createQuery(cq).getSingleResult();
			return (User)result;
		} catch(NoResultException e){
			return null;
		}
	}

	public List<User> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	public void deleteById(int id) {

		int rows = entityManager
				.createNativeQuery("DELETE FROM users WHERE idusers='"+id+"'")
				.executeUpdate();
		System.out.println(rows + " row(s) deleted in User Table.");

	}

	public void update(int iduders, String username, String password, int enabled, String email, int age) {

		int rows = entityManager
				.createNativeQuery(
						"UPDATE users SET username='" + username + "', password='" + password + "', enabled='" + enabled
								+ "', email='" + email + "', age='" + age + "' WHERE idusers='" + iduders + "'")
				.executeUpdate();
		System.out.println(rows + " row(s) updated with new data in User Table.");

	}

	public void insertLastActionDate(java.util.Date date, int id) {
		int rows = entityManager
				.createNativeQuery("UPDATE users SET lastOperationDate='" + date + "' WHERE idusers='" + id + "'")
				.executeUpdate();
		System.out.println(rows + " row(s) updated with new data in User Table.");
	}

}
