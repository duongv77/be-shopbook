package duong.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetailDTO {
	private Integer Id;
	
	private PromotionDTO promotion;
	//2 trường khóa ngoại
}
