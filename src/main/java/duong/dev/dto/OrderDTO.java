package duong.dev.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderDTO {
	private Integer id;
	
	private String address;
	
	private Date createDate;
	
	private Integer total;
	
	private Integer status;
	
	private List<OrderDetailDTO> orderdetail;
	
	private AccountDTO account;
	
	private Integer typeorder;
	
	private String description;
}
