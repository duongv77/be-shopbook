package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AuthorDTO;
import duong.dev.entity.Author;
import duong.dev.exception.AppException;
import duong.dev.mapper.AuthorMapper;
import duong.dev.repository.AuthorRepository;
import duong.dev.service.AuthorInterface;

@Service
public class AuthorServiceImpl implements AuthorInterface{
	
	@Autowired private AuthorMapper authorMapper;
	@Autowired private AuthorRepository authorRepo;
	
	
	@Override
	public List<AuthorDTO> readAll() throws IOException {
		return convertListDTO(authorRepo.findAll());
	}

	@Override
	public AuthorDTO create(AuthorDTO authorD) throws IOException {
		Author authorE = authorMapper.convertToEntity(authorD);
		authorE.setStatus(Status.STATUS_HOAT_DONG);
		authorRepo.save(authorE);
		return authorMapper.convertToDTO(authorE);
	}

	@Override
	public AuthorDTO update(AuthorDTO authorD) throws IOException {
		authorRepo.save(authorMapper.convertToEntity(authorD));
		return authorD;
	}
	
	@Override
	public AuthorDTO delete(Author authorE) throws IOException {
		authorRepo.delete(authorE);
		return authorMapper.convertToDTO(authorE);
	}
	
	public List<AuthorDTO> convertListDTO(List<Author> listAuthorE) {
		List<AuthorDTO> listAuthorD = new ArrayList<AuthorDTO>();
		for (Author authorE : listAuthorE) {
			listAuthorD.add(authorMapper.convertToDTO(authorE));
		}
		return listAuthorD;
	}
	
	public AuthorDTO getOnly(Integer id) {
		Author author = authorRepo.getById(id);
		if(author==null) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Tác giả không tồn tại");
		}
		return authorMapper.convertToDTO(author);
	}

	

}
