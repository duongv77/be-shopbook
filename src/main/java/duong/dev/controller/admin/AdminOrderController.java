package duong.dev.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.UpdateStatusVsAddressOrderDTO;
import duong.dev.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminOrderController {
	@Autowired private OrderServiceImpl orderServiceImpl;
	
	@GetMapping("/v2/admin/order/{orderstatus}")
	private ResponseEntity<ResponseDTO> getListOrderbyStatus(@PathVariable("orderstatus") Integer status) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderServiceImpl.getListOrderByStatus(status))
                .build());
	}
	
	@PutMapping("/v2/admin/order")
	private ResponseEntity<ResponseDTO> updateOrder(@RequestBody UpdateStatusVsAddressOrderDTO orderDTO) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderServiceImpl.updateStatusVsAddress(orderDTO))
                .build());
	}
}
