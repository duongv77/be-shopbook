package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.AccountDTO;
import duong.dev.entity.Account;

public interface AccountInterface {
	
	public List<AccountDTO> readAll() throws IOException;
	
	public AccountDTO create(AccountDTO accountD) throws IOException;
	
	public AccountDTO delete(Account accountE) throws IOException;
}
