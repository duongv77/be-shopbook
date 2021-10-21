package duong.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	public List<Order> findByStatus(Integer status);
}