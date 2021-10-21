package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer>{

}
