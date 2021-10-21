package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.RoleAccountDTO;
import duong.dev.entity.RoleAccount;
import duong.dev.mapper.RoleAccountMapper;
import duong.dev.repository.RoleAccountRepository;
import duong.dev.service.RoleAccountInterface;

@Service
public class RoleAccountServiceImpl implements RoleAccountInterface {
	@Autowired
	RoleAccountRepository roleAccountRepo;
	@Autowired
	RoleAccountMapper roleAccountMapper;
	 
	public List<RoleAccountDTO> convertListDTO(List<RoleAccount> listRoleAccountE ) {
		List<RoleAccountDTO> listRoleAccountD = new ArrayList<RoleAccountDTO>();
		for(RoleAccount roleAccountE : listRoleAccountE ) {
			listRoleAccountD.add(roleAccountMapper.convertToDTO(roleAccountE));
		}
		return listRoleAccountD;
	}

	@Override
	public RoleAccount create(RoleAccount roleAccount) throws IOException {
		roleAccountRepo.save(roleAccount);
		return roleAccount;
	}

	@Override
	public RoleAccountDTO update(RoleAccountDTO roleAccountD) throws IOException {
		roleAccountRepo.save(roleAccountMapper.convertToEntity(roleAccountD));
		return roleAccountD;
	}

	@Override
	public RoleAccountDTO delete(RoleAccount roleAccountE) throws IOException {
		roleAccountRepo.delete(roleAccountE);
		return roleAccountMapper.convertToDTO(roleAccountE);
	}

	@Override
	public List<RoleAccountDTO> readAll() throws IOException {
		return convertListDTO(roleAccountRepo.findAll());
	}
	
}






















