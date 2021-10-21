package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.RoleAccountDTO;
import duong.dev.entity.RoleAccount;

public interface RoleAccountInterface {
	public List<RoleAccountDTO> readAll() throws IOException;
	
	public RoleAccount create(RoleAccount roleAccount) throws IOException;
	
	public RoleAccountDTO update(RoleAccountDTO roleAccountD) throws IOException;
	
	public RoleAccountDTO delete(RoleAccount roleAccountE) throws IOException;
}
