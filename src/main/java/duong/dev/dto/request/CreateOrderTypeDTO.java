package duong.dev.dto.request;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreateOrderTypeDTO {
	private String description;
	private List<OrderTypeDTO> listOrder;
}
