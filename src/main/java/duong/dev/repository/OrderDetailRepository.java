package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.OrderDetail;



@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
}
