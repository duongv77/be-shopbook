package duong.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Product;
import duong.dev.entity.ProductCategoridetail;

@Repository
public interface ProductCategoridetailRepository extends JpaRepository<ProductCategoridetail, Integer>{
	public List<ProductCategoridetail> findByProduct(Product product);
}
