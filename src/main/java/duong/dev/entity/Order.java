package duong.dev.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "address")
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "typeorder")
	private Integer typeorder;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne
	@JoinColumn(
			name="account_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Account account;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderdetail;
	
	@OneToMany(mappedBy = "order")
	private List<OrderStatusHistory> orderstatushistorys;
	
}
