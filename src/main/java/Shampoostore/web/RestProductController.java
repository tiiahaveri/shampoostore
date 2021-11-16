package Shampoostore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Shampoostore.domain.Category;
import Shampoostore.domain.CategoryRepository;
import Shampoostore.domain.Product;
import Shampoostore.domain.ProductRepository;

@RestController
public class RestProductController {
	
	@Autowired
	private ProductRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	//REST service for all products
	@GetMapping(value="/products")
	public @ResponseBody List <Product> getProductsRest(){
		return (List<Product>) brepository.findAll();
		
	}
	//REST service for saving new product
	@PostMapping(value="/products")
	public @ResponseBody Product saveProductRest(@RequestBody Product product) {
		return brepository.save(product);
	}
	//REST service for listing all categories
	@GetMapping(value="/categories")
	public @ResponseBody List<Category>getCategoriesRest(){
		return (List<Category>) crepository.findAll();
	}
	//REST service for saving new category
	@PostMapping(value="/categories")
	public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {
		return crepository.save(category);
	}
	//REST service for finding category by id
	@GetMapping(value="/categories/{id}")
	public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long id){
		return crepository.findById(id);
	}
	//REST service for listing all products in exicting category
	@GetMapping(value="/categories/{categoryId}/products")
	public @ResponseBody List<Product> getAllProductsFromCategory(@PathVariable("categoryId")Long id){
	return crepository.findById(id).get().getProducts();
	
	}
}
