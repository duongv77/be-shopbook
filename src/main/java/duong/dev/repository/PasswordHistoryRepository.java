package duong.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import duong.dev.entity.PasswordHistory;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Integer>{
	
	@Query("SELECT e FROM PasswordHistory e WHERE e.account.id = :id and e.status=0")
	public List<PasswordHistory> findByAccountSatusOff(@Param("id") Integer id);
	
	@Query("SELECT e FROM PasswordHistory e WHERE e.account.id = :id and e.status=1")
	public PasswordHistory findByAccountSatusOn(@Param("id") Integer id);
}
