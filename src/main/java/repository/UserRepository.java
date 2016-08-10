package repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import model.User;
import java.util.*;

public interface UserRepository extends Repository<User, Integer> {

    void delete(User deleted);
    
    List<User> findAll();
 
    User findById(Integer id);
 
    User save(User persisted);
    
    User findByUsername(String username);
    
    @Modifying
    @Query(value = "INSERT INTO user_roles(username, role) VALUES (?1,?2)", nativeQuery = true)
    void addRole(String username, String role);
    
}
