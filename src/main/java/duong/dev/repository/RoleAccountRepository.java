package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import duong.dev.entity.RoleAccount;

@Repository
public interface RoleAccountRepository extends JpaRepository<RoleAccount, Integer>{

}
