package duong.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findByUsername(String username);
	public Account findByEmail(String email);
	public Optional<Account> findById(Integer id);
}
