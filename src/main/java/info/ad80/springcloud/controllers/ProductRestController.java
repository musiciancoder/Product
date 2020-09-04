package info.ad80.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.ad80.springcloud.model.Product;
import info.ad80.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	private ProductRepo repo;
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return repo.save(product);
		
	}

}
