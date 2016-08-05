package service;

import java.util.List;
import java.util.Set;

import dto.UserDTO;

public interface UserService {
	
	public void addUser(UserDTO user);
	public UserDTO findUserById(int id);
	public List<UserDTO> findAllUsers();
	public void deleteUserById(int id);
	public UserDTO findUserByName(String name);
	public void updateUser(int iduders, String username, String password, int enabled, String email, int age);
	public void registerUser(UserDTO user);
	public Set<Integer> getUsersIds(List<UserDTO> users);
	public Set<String> getUsersName(List<UserDTO> users);
}
