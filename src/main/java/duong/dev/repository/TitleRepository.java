package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer>{

}
