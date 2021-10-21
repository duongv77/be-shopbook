package duong.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Cart;
import duong.dev.entity.Cartdetail;

@Repository
public interface CartdetailRepository extends JpaRepository<Cartdetail, Integer>{
	 @Query("select o from Cartdetail o where o.product.id =:idProduct and o.cart.id=:idCart")
	 public Cartdetail findCartDetailByProductAndCart(@Param("idProduct") Integer idProduct,@Param("idCart") Integer idCart);
	 
	 @Query("select o from Cartdetail o where o.cart.id=:id ")
	 List<Cartdetail> findCartDetailByIdCart(@Param("id") Integer id);
	 
	 @Query("SELECT entity FROM Cartdetail entity WHERE entity.cart.account.id = :id")
	 public List<Cartdetail> findByAccountID(@Param("id") Integer id);
	 
	 public List<Cartdetail> findByCart(Cart cart);
	 
}

