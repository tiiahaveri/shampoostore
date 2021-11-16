package Shampoostore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import Shampoostore.domain.Category;
import Shampoostore.domain.CategoryRepository;
import Shampoostore.domain.Product;
import Shampoostore.domain.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ShampooStoreTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ProductRepository prepository;
	
	@Autowired
	private CategoryRepository crepository;
	


	
	
		@Test
		public void whenFindByProductname_ShouldReturnProductname() {
			Category c1= new Category("Testishampoot");
			Product testiShampoo= new Product("Testishampoo","Shampoo testausta varten",20.0,13.0, c1);
			crepository.save(c1);
			prepository.save(testiShampoo);
			List<Product> products= prepository.findByProductname("Testishampoo");
			
			assertThat(products.get(0).getProductname().equals("Testishampoo"));
			
		
		}
		@Test
		@Rollback(false)
		public void deleteProductShouldRemoveProduct() {
			Product p= prepository.findById(Long.valueOf(4)).get();
			prepository.delete(p);
			
			Optional<Product> deleteProduct= prepository.findById(Long.valueOf(4));
			assertThat(deleteProduct).isEmpty();
			
			
			
		}
		@Test
		public void sampleTest() {
		Category c1= new Category("Testishampoot");
		Product test1= new Product("Testishampoo", "Tällä testataan koodia", 20.0,10.0, c1);
		Product test2=new Product("Testihoitsikka","Tällä testataan koodinpätkää", 22.0,11.0,c1);
		crepository.save(c1);
		prepository.save(test1);
		prepository.save(test2);
		
		List<Product> result= prepository.findByProductname("Testishampoo");
		
		assertEquals(result.size(),1);
		
		}
		@Test
		public void shouldUpdateProductById() {
			Category c1= new Category("Testishampoot");
			crepository.save(c1);
			Product test1= new Product("Testishampoo", "Tällä testataan koodia", 20.0,10.0, c1);
			entityManager.persist(test1);
			
			Product test2=new Product("Testihoitsikka","Tällä testataan koodinpätkää", 22.0,11.0,c1);
			entityManager.persist(test2);
			
			Product updatedProduct= new Product("updated testishampoo","updated testikuvaus", 21.0,11.0,c1);
			
			Product prod= prepository.findById(test2.getProductid()).get();
			prod.setProductname(updatedProduct.getProductname());
			prod.setDescription(updatedProduct.getDescription());
			prod.setBuyingprice(updatedProduct.getBuyingprice());
			prod.setSellingprice(updatedProduct.getSellingprice());
			prod.setCategory(updatedProduct.getCategory());
			
			prepository.save(prod);
			
			Product checkProd=prepository.findById(test2.getProductid()).get();
			
			assertThat(checkProd.getProductid()).isEqualTo(test2.getProductid());
			assertThat(checkProd.getProductname()).isEqualTo(test2.getProductname());
			assertThat(checkProd.getDescription()).isEqualTo(test2.getDescription());
			
			
		}

}

