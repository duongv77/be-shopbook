package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "status")
	private Integer status;
	
	@OneToMany(mappedBy = "categorie")
	private List<Categorie> categories;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(
			name = "category_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Categorie categorie;
	
	@OneToMany(mappedBy = "categorie")
	private List<ProductCategoridetail> productCategoridetail;
}
