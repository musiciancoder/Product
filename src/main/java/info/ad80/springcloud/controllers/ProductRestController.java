package info.ad80.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import info.ad80.springcloud.dto.Coupon;
import info.ad80.springcloud.model.Product;
import info.ad80.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private RestTemplate restTemplate; //Comunicar con otro micrservicio
	
	@Value("${couponService.url}") //desde application.properties
	private String couponServiceURL;
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		//Obtener el cupon de descuento del otro microservicio; CouponCode es SUPERSALE en body de postman.
		//couponServiceURL + product.getCouponCode() es lo mismoq http://localhost:9091/couponapi/coupons/SUPERSALE, para obtener el cupon en postman
		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);  //se crea un Coupon en package dto, con los mismos atributos de la clase modelo del otro servicio
		product.setPrice(product.getPrice().subtract(coupon.getDiscount())); //operacion aritmetica para aplicar descuento al precio
		return repo.save(product);
		
	}

}
