package duong.dev.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UpdateQuantityCartdetailDTO {
	@NotBlank(message="Thiếu id cartdetail")
	private Integer id;
	
	@NotBlank(message="Thiếu số lượng sản phẩm")
	private Integer quantity;
}
