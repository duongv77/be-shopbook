package duong.dev.dto.request;

import org.springframework.stereotype.Component;

import duong.dev.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderTypeDTO {
	private Integer quantity;
	private ProductDTO product;
}
