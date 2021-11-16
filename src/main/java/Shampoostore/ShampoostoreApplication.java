package Shampoostore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Shampoostore.domain.Category;
import Shampoostore.domain.CategoryRepository;

import Shampoostore.domain.Product;
import Shampoostore.domain.ProductRepository;

import Shampoostore.domain.User;
import Shampoostore.domain.UserRepository;


@SpringBootApplication
public class ShampoostoreApplication {
	
	private static final Logger log= LoggerFactory.getLogger(ShampoostoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ShampoostoreApplication.class, args);
		
		
		
	}
	@Bean
	public CommandLineRunner demo(CategoryRepository crepository, ProductRepository prepository, UserRepository uRepository) {
		return(args) -> {
			log.info("Tallennetaan uusia kategorioita");
			Category k1= new Category("Shampoo");
			Category k2= new Category("Hoitoaine");
			Category k3= new Category("Hiuksiin jätettävät");
			
			crepository.save(k1);
			crepository.save(k2);
			crepository.save(k3);
			
			log.info("Tallennetaan käyttäjiä");
			uRepository.save(new User ("user", "$2a$10$gYhRVPCVkxonw4C6KbzBHOGfN3tR0cq8S51Ojct3iKEtP7fBCeZke", "USER"));
			uRepository.save(new User ("admin", "$2a$04$g6UsslKArS7jZrevnIpQIe5udQCOSriLtEz19jDRFUVPM74FT2rvG", "ADMIN"));
			

			
			
			
			log.info("Tallennetaan tuotteita");
			Product p1= new Product("Tuuheuttava shampoo","Antaa tuuheutta",23.0,13.0,k1);
			Product p2= new Product("Kosteuttava hoitoaine","Kosteuttaa ja elvyttää vahingoittuneita hiuksia", 24.0,12.0,k2);
			Product p3= new Product("Extra Strong hiuslakka","Antaa kestävää pitoa ja viimeistelee", 25.0,12.0,k3);
			
			prepository.save(p1);
			prepository.save(p2);
			prepository.save(p3);

			
			log.info("Haetaan kaikki tuotteet");
			for(Product product:prepository.findAll()) {
				log.info(product.toString());
			
			}

			
			};
			
				
	}
				
				

}
