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
import duong.dev.dto.AccountDTO;
import duong.dev.dto.LoginDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Product;
import duong.dev.serviceImpl.AccountServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AccountController {
	@Autowired private AccountServiceImpl accountSV;
	
	@PostMapping("/v1/account")
	private ResponseEntity<ResponseDTO> createAccount(@RequestBody @Valid LoginDTO accountDTO) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.createAccountLogin(accountDTO))
                .build());
	}
	
	@PostMapping("/v1/login")
	private ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.login(loginDTO))
                .build());
	}
	
	@GetMapping("/v1/forget_password/{email}")
	private ResponseEntity<ResponseDTO> forgetPassword(@PathVariable("email") String email) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.forgetPassword(email))
                .build());
	}
	
	@PostMapping("/v1/change/password/{key}")
	private ResponseEntity<ResponseDTO> restartPassword(@PathVariable("key") String key, @RequestBody @Valid LoginDTO changePw) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.updatePassword(changePw,key))
                .build());
	}
	
	@GetMapping("/v1/restart/password/account_{id}")
	private ResponseEntity<ResponseDTO> restartPasswordGetAccount(@PathVariable("id") Integer  id) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.getAccountForgetPasword(id))
                .build());
	}
	
	@GetMapping("/v2/user")
	private ResponseEntity<ResponseDTO> get() throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.getAccount())
                .build());
	}

}
