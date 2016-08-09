package repository;

import org.springframework.data.repository.Repository;
import model.Product;
import java.util.*;

public interface ProductRepository extends Repository<Product, Integer>{

    void delete(Product deleted);
    
    List<Product> findAll();
 
    Product findById(Integer id);
 
    Product save(Product persisted);
}
