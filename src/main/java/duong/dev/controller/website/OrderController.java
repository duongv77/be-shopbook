package duong.dev.controller.website;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateOrderDTO;
import duong.dev.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class OrderController {
	@Autowired private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/v2/user/order")
	private ResponseEntity<ResponseDTO> createOrderAccount(
			@RequestBody @Valid CreateOrderDTO createOrderDTO
			) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderServiceImpl.createDetail(createOrderDTO))
                .build());
	}
	
	
}
