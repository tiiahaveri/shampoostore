package Shampoostore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import Shampoostore.domain.CategoryRepository;

import Shampoostore.domain.Product;
import Shampoostore.domain.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository prepository;
	
	@Autowired 
	private CategoryRepository crepository;
	
	
	//haetaan kaikki tuotteet
	@GetMapping("/productlist")
	public String productlist(Model model) {
		model.addAttribute("products",prepository.findAll());
		return "productlist";
	}
	//uuden tuotteen lisäyslomake
	@GetMapping("/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", crepository.findAll());
		return "/addproduct";
	}
	//uuden tuotteen tallennuslomake
	@PostMapping("/saveproduct")
	public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		model.addAttribute("categories", crepository.findAll());
		if(bindingResult.hasErrors()) {
			return "addproduct";
		
		}else {
		model.addAttribute("product", product);
		
		prepository.save(product);
		
		return "redirect:/productlist";
	}
		
	}

	//poisto
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deleteproduct/{id}")
	public String deleteById(@PathVariable("id")Long productid, Model model) {
		prepository.deleteById(productid);
		return "redirect:/productlist";


		
	}
	//olemassa olevan tuotteen muokkauslomake
	@GetMapping("/edit/{productid}")
	public String showUpdateForm(@PathVariable("productid")Long id, Model model) {
		model.addAttribute("product", prepository.findById(id).get());
		model.addAttribute("categories", crepository.findAll());
		return "updateproduct";
		
		
		
	}
	

		
	}

