package info.ad80.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import info.ad80.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
