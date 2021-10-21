package duong.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	@Query("SELECT e FROM Address e WHERE e.account.id = :id")
	public List<Address> findByIdAccount(@Param("id") Integer id);
}
