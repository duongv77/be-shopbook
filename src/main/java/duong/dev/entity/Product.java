package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "available")
	private Integer available;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "numberpages")
	private Integer numberpages;
	
	@Column(name = "form")
	private String form;
	
	@Column(name = "publishyear")
	private Integer publishyear;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "supplier")
	private String supplier;
	
	@Column(name = "language")
	private String language;
	
	@OneToMany(mappedBy = "product")
	private List<ProductCategoridetail> productCategoridetail;
	
	@OneToMany(mappedBy = "product")
	private List<Comment> comment;
	
	@OneToMany(mappedBy = "product")
	private List<Rate> rate;

	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderdetail;
	
	@OneToMany(mappedBy = "product")
	private List<Favorite> favorite;
	
	@OneToOne
	@JoinColumn(
			name = "author_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Author author;
	
	@OneToOne
	@JoinColumn(
			name = "region_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Region region;
	
	@OneToOne
	@JoinColumn(
			name = "title_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Title title;
	
	@OneToOne(mappedBy = "product")
	private PromotionDetail promotiondetail;
}
