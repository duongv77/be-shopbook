package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.PromotionDetailDTO;
import duong.dev.entity.PromotionDetail;
import duong.dev.mapper.PromotionDetailMapper;
import duong.dev.repository.PromotionDetailRepository;
import duong.dev.service.PromotionDetailInterface;

@Service
public class PromotionDetailServiceImpl implements PromotionDetailInterface {
	@Autowired
	PromotionDetailRepository promotionDetailRepo;
	@Autowired
	PromotionDetailMapper promotionDetailMapper;
		
	
	
	public List<PromotionDetailDTO> convertListDTO(List<PromotionDetail> listPromotionDetailE ) {
		List<PromotionDetailDTO> listPromotionDetailD = new ArrayList<PromotionDetailDTO>();
		for(PromotionDetail promotionDetailE : listPromotionDetailE ) {
			listPromotionDetailD.add(promotionDetailMapper.convertToDTO(promotionDetailE));
		}
		return listPromotionDetailD;
	}



	@Override
	public List<PromotionDetailDTO> readAll() throws IOException {
		return convertListDTO(promotionDetailRepo.findAll());
	}



	@Override
	public PromotionDetailDTO create(PromotionDetailDTO promotionDetailD) throws IOException {
		PromotionDetail promotionDetailE = promotionDetailMapper.convertToEntity(promotionDetailD);
		promotionDetailRepo.save(promotionDetailE);
		return promotionDetailMapper.convertToDTO(promotionDetailE);
	}



	@Override
	public PromotionDetailDTO update(PromotionDetailDTO promotionDetailD) throws IOException {
		promotionDetailRepo.save(promotionDetailMapper.convertToEntity(promotionDetailD));
		return promotionDetailD;
	}



	@Override
	public PromotionDetailDTO delete(PromotionDetail promotionDetailE) throws IOException {
		promotionDetailRepo.delete(promotionDetailE);
		return promotionDetailMapper.convertToDTO(promotionDetailE);
	}
	

}
