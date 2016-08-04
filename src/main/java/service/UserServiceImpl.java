package service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import model.User;
import util.serviceUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDaoImpl;
	
	@Transactional
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDaoImpl.add(user);
	}

	@Transactional(readOnly = true)
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userDaoImpl.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userDaoImpl.findAll();
	}
	
	@Transactional
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDaoImpl.deleteById(id);
	}

	@Transactional(readOnly = true)
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return userDaoImpl.findByName(name);
	}

	@Transactional
	public void updateUser(int iduders, String username, String password, int enabled, String email, int age) {
		// TODO Auto-generated method stub
		userDaoImpl.update(iduders, username, password, enabled, email, age);
	}

	@Transactional
	public void registerUser(User user) {
		Set<Integer> usersIds = getUsersIds(userDaoImpl.findAll());
		user.setEnabled(1);
		user.setIduders((Integer)usersIds.toArray()[usersIds.size()-1]+1);
		user.setLastOperationDate(serviceUtils.getCurrentDate());
		userDaoImpl.add(user);
		userDaoImpl.addRole(user.getUsername(),"ROLE_USER");		
	}
	
	@Transactional(readOnly = true)
	public Set<Integer> getUsersIds(List<User> users) {		
		Set<Integer> result=users.stream().map(User::getIduders).collect(Collectors.toSet());		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Set<String> getUsersName(List<User> users) {		
		Set<String> result=users.stream().map(User::getUsername).collect(Collectors.toSet());		
		return result;
	}
	
}
