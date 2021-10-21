package duong.dev.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.CategorieDTO;
import duong.dev.dto.ProductCategoridetailDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;
import duong.dev.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminProductController {
	@Autowired private ProductServiceImpl productServiceImpl;
	
	@PostMapping("/v2/admin/product/{id}")
	private ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody ProductDTO productD, @PathVariable("id") Categorie categorie) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productServiceImpl.create(productD, categorie))
				.build());
	}
	
	@GetMapping("/v2/admin/product")
	private ResponseEntity<ResponseDTO> getAll() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productServiceImpl.readAll())
				.build());
	}
	
	@PutMapping("/v2/admin/product")
	private ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productServiceImpl.update(productDTO))
				.build());
	} 
	
	@DeleteMapping("/v2/admin/product/{id}")
	private ResponseEntity<ResponseDTO> deleteProduct(@PathVariable("id") Product product) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productServiceImpl.delete(product))
				.build());
	} 
}
