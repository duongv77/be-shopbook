package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}