package duong.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
	@Query("SELECT e FROM Categorie e WHERE id = :id")
	public List<Categorie> findCategoryBig(@Param("id") Integer id);
}
