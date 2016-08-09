package repository;

import org.springframework.data.repository.Repository;
import model.User;
import java.util.*;

public interface UserRepository extends Repository<User, Integer> {

    void delete(User deleted);
    
    List<User> findAll();
 
    Optional<User> findOne(Integer id);
 
    User save(User persisted);
}
