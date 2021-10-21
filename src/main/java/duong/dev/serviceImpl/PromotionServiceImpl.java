package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.PromotionDTO;
import duong.dev.entity.Promotion;
import duong.dev.mapper.PromotionMapper;
import duong.dev.repository.PromotionRepository;
import duong.dev.service.PromotionInterface;

@Service
public class PromotionServiceImpl implements PromotionInterface {
	@Autowired
	PromotionRepository promotionRepo;
	@Autowired
	PromotionMapper promotionMapper;
		
	
	
	public List<PromotionDTO> convertListDTO(List<Promotion> listPromotionE ) {
		List<PromotionDTO> listPromotionD = new ArrayList<PromotionDTO>();
		for(Promotion promotionE : listPromotionE ) {
			listPromotionD.add(promotionMapper.convertToDTO(promotionE));
		}
		return listPromotionD;
	}



	@Override
	public List<PromotionDTO> readAll() throws IOException {
		return convertListDTO(promotionRepo.findAll());
	}



	@Override
	public PromotionDTO create(PromotionDTO promotionD) throws IOException {
		Promotion promotionE = promotionMapper.convertToEntity(promotionD);
		promotionRepo.save(promotionE);
		return promotionMapper.convertToDTO(promotionE);
	}



	@Override
	public PromotionDTO update(PromotionDTO promotionD) throws IOException {
		promotionRepo.save(promotionMapper.convertToEntity(promotionD));
		return promotionD;
	}



	@Override
	public PromotionDTO delete(Promotion promotionE) throws IOException {
		promotionRepo.delete(promotionE);
		return promotionMapper.convertToDTO(promotionE);
	}
	

}
