package duong.dev.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import duong.dev.dto.CartdetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreateOrderDTO {
	@NotBlank(message = "Không được để trống địa chỉ")
	private String address;
	
	private List<CartdetailDTO> cartdetail;
}
