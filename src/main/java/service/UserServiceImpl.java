package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import dto.UserDTO;
import model.User;
import util.serviceUtils;
import converter.*;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDaoImpl;
	
	@Transactional
	public void addUser(UserDTO user) {
		// TODO Auto-generated method stub
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userDaoImpl.add(persistentUser);
	}

	@Transactional(readOnly = true)
	public UserDTO findUserById(int id) {
		// TODO Auto-generated method stub
		User user =  userDaoImpl.findById(id);
		UserDTO dtoUser = PersistentToDtoConversion.convertUserToUserDTO(user);
		return dtoUser;
	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAllUsers() {
		// TODO Auto-generated method stub
		List<UserDTO> users = getDtoUsers(userDaoImpl.findAll());
		return users;
	}
	
	@Transactional
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDaoImpl.deleteById(id);
	}

	@Transactional(readOnly = true)
	public UserDTO findUserByName(String name) {
		// TODO Auto-generated method stub
		try{
			User user = userDaoImpl.findByName(name);
			return PersistentToDtoConversion.convertUserToUserDTO(user);
		}catch(Exception e){
			return null;
		}
		
	}

	@Transactional
	public void updateUser(int iduders, String username, String password, int enabled, String email, int age) {
		// TODO Auto-generated method stub
		userDaoImpl.update(iduders, username, password, enabled, email, age);
	}

	@Transactional
	public void registerUser(UserDTO user) {
		List<User> users = userDaoImpl.findAll();
		List<UserDTO> dtoUsers = getDtoUsers(users);	
		Set<Integer> usersIds = getUsersIds(dtoUsers);
		user.setEnabled(1);
		user.setIduders((Integer)usersIds.toArray()[usersIds.size()-1]+1);
		user.setLastOperationDate(serviceUtils.getCurrentDate());
		User persistentUser =DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userDaoImpl.add(persistentUser);
		userDaoImpl.addRole(persistentUser.getUsername(),"ROLE_USER");		
	}
	
	@Transactional(readOnly = true)
	public Set<Integer> getUsersIds(List<UserDTO> users) {		
		Set<Integer> result=users.stream().map(UserDTO::getIduders).collect(Collectors.toSet());		
		return result;
	}
	
	@Transactional(readOnly = true)
	public Set<String> getUsersName(List<UserDTO> users) {		
		Set<String> result=users.stream().map(UserDTO::getUsername).collect(Collectors.toSet());		
		return result;
	}
	
	private List<UserDTO> getDtoUsers(List<User> users){
		List<UserDTO> dtoUsers = new ArrayList<UserDTO>();
		for(User u:users){
			UserDTO userDto = PersistentToDtoConversion.convertUserToUserDTO(u);
			dtoUsers.add(userDto);
		}
		
		return dtoUsers;
	}
}
