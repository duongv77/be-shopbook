package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.AddressDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Address;
import duong.dev.mapper.AddressMapper;
import duong.dev.repository.AddressRepository;
import duong.dev.service.AddressInterface;

@Service
public class AddressServiceImpl implements AddressInterface{
	
	@Autowired private AddressMapper addressMapper;
	@Autowired private AddressRepository addressRepo;

	@Override
	public List<AddressDTO> readAll(Account accountE) throws IOException {
		return convertListDTO(addressRepo.findByIdAccount(accountE.getId()));
	}

	@Override
	public AddressDTO create(AddressDTO addressD) throws IOException {
		Address addressE = addressMapper.convertToEntity(addressD);
		addressRepo.save(addressE);
		return addressMapper.convertToDTO(addressE);
	}
	
	@Override
	public AddressDTO update(AddressDTO addressD) throws IOException {
		addressRepo.save(addressMapper.convertToEntity(addressD));
		return addressD;
	}

	@Override
	public AddressDTO delete(Address addressE) throws IOException {
		addressRepo.delete(addressE);
		return addressMapper.convertToDTO(addressE);
	}
	
	public List<AddressDTO> convertListDTO(List<Address> listAddressE) {
		List<AddressDTO> listAddressD = new ArrayList<AddressDTO>();
		for (Address addressE : listAddressE) {
			listAddressD.add(addressMapper.convertToDTO(addressE));
		}
		return listAddressD;
	}

	

}
