package Shampoostore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Shampoostore.domain.Category;
import Shampoostore.domain.CategoryRepository;


@Controller
public class CategoryController {

	
	@Autowired
	private CategoryRepository crepository;
	
	//@Autowired
	//private TuoteRepository trepository;
	
	//Haetaan kaikki kategoriat
	@GetMapping("/categorylist")
	public String categoryList(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
				
	}
	
	//pyydetään "lisää uusi kategoria -lomake"
	@GetMapping("/addcategory")
		public String NewCategory(Model model) {
			model.addAttribute("category", new Category());
			
			return "addcategory";
		}
	//tallennetaan lomakkeelle syötetty uusi kategoria
	@PostMapping("/savecategory")
	public String tallennaKategoria(@ModelAttribute("category")Category category) {
		crepository.save(category);
		return "redirect:categorylist";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deletecategory/{id}")
		public String deleteCategory(@PathVariable("id")Long categoryid, Model model) {
			crepository.deleteById(categoryid);
			return "redirect:/categorylist";
		
	}
		
	}
	

