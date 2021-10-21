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
@Table(name = "orderdetails")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "sale")
	private Integer sale;
	
	@OneToOne
	@JoinColumn(
		name = "order_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Order order;
	
	@OneToOne
	@JoinColumn(
		name = "product_id",
		nullable = false,
		referencedColumnName = "id"
	)
	private Product product;
}
