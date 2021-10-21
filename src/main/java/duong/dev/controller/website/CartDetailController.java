package duong.dev.controller.website;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.JwtTokenUtil;
import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.AuthorDTO;
import duong.dev.dto.CartdetailDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.UpdateQuantityCartdetailDTO;
import duong.dev.entity.Author;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;
import duong.dev.mapper.CartdetailMapper;
import duong.dev.repository.ProductRepository;
import duong.dev.serviceImpl.CartdetailServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class CartDetailController {
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	CartdetailServiceImpl cartdetailImpl;
	@Autowired
	CartdetailMapper cartdetailMapper;
	
	
	@GetMapping("/v2/user/cartdetail")
	private ResponseEntity<ResponseDTO> readAll() throws IOException, ServletException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cartdetailImpl.readAll())
                .build());
	}
	
	
	@GetMapping("/v2/user/addtocart/product/{id}")
	private ResponseEntity<ResponseDTO> addToCart(@PathVariable("id") Product product) throws IOException, ServletException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cartdetailImpl.create(product))
                .build());
	}
	
	@PutMapping("/v2/user/cartdetail/quantity")
	private ResponseEntity<ResponseDTO> updateQuantityCartdetail(@RequestBody UpdateQuantityCartdetailDTO updateQuantityCartdetailDTO) throws IOException, ServletException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cartdetailImpl.updateQuantity(updateQuantityCartdetailDTO))
                .build());
	}
}
