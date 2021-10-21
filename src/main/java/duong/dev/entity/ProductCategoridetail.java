package duong.dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productcategoridetails")
public class ProductCategoridetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(
			name = "category_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Categorie categorie;
	
	@OneToOne
	@JoinColumn(
			name = "product_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Product product;
}
