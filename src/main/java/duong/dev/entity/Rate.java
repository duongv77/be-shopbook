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
@Table(name = "rates")
public class Rate {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="vote")
	private Integer vote;
	
	@Column(name = "status")
	private Integer status;
	
	@OneToOne
	@JoinColumn(
		name = "account_id", 
		nullable = false,
		referencedColumnName = "id"
	)			
	private Account account;
	
	@OneToOne
	@JoinColumn(
		name = "product_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Product product;
}
