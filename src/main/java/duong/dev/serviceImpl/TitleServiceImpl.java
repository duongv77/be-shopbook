package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.TitleDTO;
import duong.dev.entity.Title;
import duong.dev.mapper.TitleMapper;
import duong.dev.repository.TitleRepository;
import duong.dev.service.TitleInterface;

@Component
public class TitleServiceImpl implements TitleInterface{
	@Autowired private TitleRepository titleRepo;
	@Autowired private TitleMapper titleMapper;
	@Override
	public List<TitleDTO> showAll() {
		return convertListDTO(titleRepo.findAll());
	}
	
	private List<TitleDTO> convertListDTO(List<Title> titles) {
		List<TitleDTO> titleDTOs = new ArrayList<TitleDTO>();
		for (Title title : titles) {
			titleDTOs.add(titleMapper.convertToDTO(title));
		}
		return titleDTOs;
	}

}
