package duong.dev.controller.admin;

import java.io.IOException;

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
import duong.dev.dto.AuthorDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Author;
import duong.dev.mapper.AuthorMapper;
import duong.dev.serviceImpl.AuthorServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminAuthorController {
	@Autowired private AuthorServiceImpl authorSV;
	@Autowired AuthorMapper authorMapper;
	
	@PostMapping("/v2/admin/author")
	private ResponseEntity<ResponseDTO> create(@Valid @RequestBody AuthorDTO authorD) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.create(authorD))
                .build());
	}
	
	@PutMapping("/v2/admin/author")
	private ResponseEntity<ResponseDTO> update(@Valid @RequestBody AuthorDTO authorD) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.create(authorD))
                .build());
	}
	
	@DeleteMapping("/v2/admin/author/{id}")
	private ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id,
			Author author) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.delete(author))
                .build());
	}
	
	@GetMapping("/v2/admin/author")
	private ResponseEntity<ResponseDTO> readAll() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.readAll())
                .build());
	}
	@GetMapping("/v2/admin/author/{id}")
	private ResponseEntity<ResponseDTO> getOnly(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.getOnly(id))
                .build());
	}
}
