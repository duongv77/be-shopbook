package duong.dev.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
import duong.dev.common.CommonRole;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.AccountTokenDTO;
import duong.dev.dto.LoginDTO;
import duong.dev.entity.Account;
import duong.dev.entity.PasswordHistory;
import duong.dev.entity.RoleAccount;
import duong.dev.exception.AppException;
import duong.dev.libs.HashUtil;
import duong.dev.mapper.AccountMapper;
import duong.dev.repository.AccountRepository;
import duong.dev.service.AccountInterface;
import duong.dev.util.JwtUserDetailsService;
import duong.dev.util.Roandom;
import duong.dev.util.SendMail;

@Service
public class AccountServiceImpl implements AccountInterface {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RoleAccountServiceImpl accountServiceImpl;
	@Autowired
	private PasswordHistoryServiceImpl passwordHistoryServiceImpl;
//	@Autowired
//	private CartServiceImpl cartServiceImpl;

	public List<AccountDTO> convertListDTO(List<Account> listAccountE) {
		List<AccountDTO> listAccountD = new ArrayList<AccountDTO>();
		for (Account accountE : listAccountE) {
			listAccountD.add(accountMapper.convertToDTO(accountE));
		}
		return listAccountD;
	}

	@Override
	public List<AccountDTO> readAll() throws IOException {
		List<Account> listAccountE = accountRepo.findAll();
		return convertListDTO(listAccountE);
	}

	
	@Override
	public AccountDTO create(AccountDTO accountD) throws IOException {
		accountD.setActivated(Status.STATUS_HOAT_DONG);
		Account accountE = accountMapper.convertToEntity(accountD);
		accountRepo.save(accountE);
		return accountMapper.convertToDTO(accountE);
	}
	
	//tạo user đăng nhập chưa có thông tin
	public AccountDTO createAccountLogin(LoginDTO accountD) throws IOException {
		if(accountRepo.findByUsername(accountD.getUsername())!=null) 
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Username đã được sử dụng");
		if(accountRepo.findByEmail(accountD.getEmail())!=null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Email đã được sử dụng");
		
		Account accountE = new Account();
		accountE.setUsername(accountD.getUsername());
		accountE.setActivated(Status.STATUS_HOAT_DONG);
		accountE.setPassword(HashUtil.hash(accountD.getPassword()));
		accountE.setFullname(accountD.getFullname());
		accountE.setEmail(accountD.getEmail());
		accountE.setPhoto(Status.PHOTO_AVT); 
		accountRepo.save(accountE);
		
//		cartServiceImpl.create(accountE);
		
		RoleAccount roleAccount = new RoleAccount();
		roleAccount.setAccount(accountE);
		roleAccount.setRole(CommonRole.ROLE_USER);
		accountServiceImpl.create(roleAccount);
		
		SendMail.senMaiChaoMung(accountE.getUsername(), accountD.getPassword(), accountE.getEmail());
		
		return accountMapper.convertToDTO(accountE);
	}

	@Override
	public AccountDTO delete(Account accountE) throws IOException {
		accountRepo.delete(accountE);
		return accountMapper.convertToDTO(accountE);
	}

	public ResponseEntity updatePassword(LoginDTO accountChangPw, String key) throws IOException {
		Account accountE = accountRepo.findByUsername(accountChangPw.getUsername());
		if(accountE==null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		PasswordHistory passwordHistory = passwordHistoryServiceImpl.get(accountE);
		if(passwordHistory==null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Xảy ra lỗi");
		LocalDateTime dateTime= LocalDateTime.parse(passwordHistory.getDatetime(), formatter);
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		long betweenTime = java.time.Duration.between(timeNow, dateTime).toMinutes();
		if(!key.equalsIgnoreCase(passwordHistory.getCodeComfirm()))
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn không có quyền đổi mật khẩu này!");
		if(betweenTime<0)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đã hết thời gian đổi mật khẩu");
		accountE.setPassword(HashUtil.hash(accountChangPw.getPassword()));
		accountRepo.save(accountE);
		passwordHistoryServiceImpl.deleteAllOff(accountE);
		passwordHistoryServiceImpl.updateStatus(accountE);
		return null;
	}
	
	public ResponseEntity forgetPassword(String email) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		Account account = accountRepo.findByEmail(email);
		if(account==null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		
		String strRoandom = Roandom.randomNumberAndString();
		passwordHistoryServiceImpl.deleteAllOff(account);
		passwordHistoryServiceImpl.create(account, strRoandom);
		
		String endUrl = account.getId()+"/"+strRoandom;
		
		SendMail.forgetPassword(email, endUrl);
		return null;
	}

	public AccountDTO getAccountForgetPasword(Integer id) {
		Optional<Account> accountOp = accountRepo.findById(id);
		if(!accountOp.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		
		Account account= accountOp.get();
		AccountDTO accountDTO =new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setFullname(account.getFullname()==null? account.getUsername(): account.getFullname());
		accountDTO.setPhoto(account.getPhoto());
		accountDTO.setUsername(account.getUsername());
		return accountDTO;
	}
	
	public AccountDTO updateProfile(AccountDTO accountD) throws IOException {
		Account accountE = accountRepo.getById(accountD.getId());
		if (accountE == null)
			return null;
		accountD.setPassword(accountE.getPassword());
		accountRepo.save(accountMapper.convertToEntity(accountD));
		return accountMapper.convertToDTO(accountE);
	}

	public AccountTokenDTO login(LoginDTO accounLogin) throws Exception {
		Account account = accountRepo.findByUsername(accounLogin.getUsername());
		if(account==null) 
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		if(!HashUtil.verifile(accounLogin.getPassword(), account.getPassword())) 
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_CHINH_XAC, "Mật khẩu không chính xác");
		
		authenticate(accounLogin.getUsername(), accounLogin.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(accounLogin.getUsername());

		AccountDTO accountDTO = accountMapper.convertToDTO(account);
		AccountTokenDTO accountTokenDTO = new AccountTokenDTO();

		final String token = jwtTokenUtil.generateToken(userDetails);

		accountTokenDTO.setAccessToken(token);
		accountTokenDTO.setFullname(accountDTO.getFullname());
		accountTokenDTO.setImage(account.getPhoto());
		accountTokenDTO.setUsername(account.getUsername());
		accountTokenDTO.setRoleAccount(accountServiceImpl.convertListDTO(account.getRoleAccount()));
		return accountTokenDTO;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	public AccountDTO getAccount() throws ServletException, IOException {
		AccountDTO accountDTO = jwtTokenUtil.getUserToToken();
		Optional<Account> account = accountRepo.findById(accountDTO.getId());
		if(!account.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		}
		account.get().setPassword(null);
		return accountMapper.convertToDTO(account.get());
	}

}
