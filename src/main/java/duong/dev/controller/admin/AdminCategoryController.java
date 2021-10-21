package duong.dev.controller.admin;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Product;
import duong.dev.serviceImpl.CategorieServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminCategoryController {
	@Autowired private CategorieServiceImpl categorieServiceImpl;
	
	@GetMapping("/v2/admin/category/product_{id}")
	private  ResponseEntity<ResponseDTO> createProduct(@PathVariable("id") Product product) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(categorieServiceImpl.finByListCateToProduct(product))
				.build());
	}
}
