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
@Table(name = "passwordhistorys")
public class PasswordHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name ="datetime")
	private String datetime;
	
	@Column(name ="password")
	private String password;
	
	@Column(name ="code_comfirm")
	private String codeComfirm;
	
	@Column(name ="status")
	private Integer status;
	
	@OneToOne
	@JoinColumn(
		name = "account_id",
		nullable = false,
		referencedColumnName = "id"
	)
	private Account account;
}
