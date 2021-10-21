package duong.dev.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
	private Integer id;
	
	private String name;
	
	private Integer sale;
	
	private String createDate;
	
	private String description;
	
	private String endDate;
	
	private Integer activated;
}
