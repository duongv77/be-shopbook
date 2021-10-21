package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.PromotionDetailDTO;
import duong.dev.entity.PromotionDetail;

@Component
public class PromotionDetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public PromotionDetail convertToEntity(PromotionDetailDTO promotionDetailDTO) {
		PromotionDetail entity = new PromotionDetail();
		mapper.map(promotionDetailDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public PromotionDetailDTO convertToDTO(PromotionDetail entity) {
		PromotionDetailDTO promotionDetailDTO = new PromotionDetailDTO();
		mapper.map(entity, promotionDetailDTO);
		return promotionDetailDTO;
	}
}
