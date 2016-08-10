package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.UserDTO;
import model.User;
import repository.UserRepository;
import util.serviceUtils;
import converter.*;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public void addUser(UserDTO user) {
		// TODO Auto-generated method stub
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.save(persistentUser);
	}

	@Transactional(readOnly = true)
	public UserDTO findUserById(int id) {
		// TODO Auto-generated method stub
		User user =  userRepo.findById(id);
		UserDTO dtoUser = PersistentToDtoConversion.convertUserToUserDTO(user);
		return dtoUser;
	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAllUsers() {
		// TODO Auto-generated method stub
		List<UserDTO> users = getDtoUsers(userRepo.findAll());
		return users;
	}
	
	@Transactional
	public void deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.delete(persistentUser);
	}

	@Transactional(readOnly = true)
	public UserDTO findUserByName(String name) {
		if(userRepo.findByUsername(name)==null)
			return null;
		else
			return PersistentToDtoConversion.convertUserToUserDTO(userRepo.findByUsername(name));
	}

	@Transactional
	public void updateUser(UserDTO user) {
		User persistentUser = DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.save(persistentUser);
	}

	@Transactional
	public void registerUser(UserDTO user) {
		List<User> users = userRepo.findAll();
		List<UserDTO> dtoUsers = getDtoUsers(users);	
		Set<Integer> usersIds = getUsersIds(dtoUsers);
		user.setEnabled(1);
		user.setIduders((Integer)usersIds.toArray()[usersIds.size()-1]+1);
		user.setLastOperationDate(serviceUtils.getCurrentDate());
		User persistentUser =DtoToPersistentConversion.convertUserDtoToPersistentUser(user);
		userRepo.save(persistentUser);
		userRepo.addRole(persistentUser.getUsername(),"ROLE_USER");		
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
