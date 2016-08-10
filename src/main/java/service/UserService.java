package service;

import java.util.List;
import java.util.Set;

import dto.UserDTO;

public interface UserService {
	
	public void addUser(UserDTO user);
	public UserDTO findUserById(int id);
	public List<UserDTO> findAllUsers();
	public void deleteUser(UserDTO user);
	public UserDTO findUserByName(String name);
	public void updateUser(UserDTO user);
	public void registerUser(UserDTO user);
	public Set<Integer> getUsersIds(List<UserDTO> users);
	public Set<String> getUsersName(List<UserDTO> users);
}
