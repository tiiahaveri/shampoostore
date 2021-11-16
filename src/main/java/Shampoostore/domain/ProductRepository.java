package Shampoostore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface ProductRepository extends CrudRepository<Product, Long> {
	
	//Product findByProductname(String productname);
	List<Product> findByProductname (String productname);

}
