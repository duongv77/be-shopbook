package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.PromotionDetailDTO;
import duong.dev.entity.PromotionDetail;

public interface PromotionDetailInterface {
	public List<PromotionDetailDTO> readAll() throws IOException;
	
	public PromotionDetailDTO create(PromotionDetailDTO promotionDetailD) throws IOException;
	
	public PromotionDetailDTO update(PromotionDetailDTO promotionDetailD) throws IOException;
	
	public PromotionDetailDTO delete(PromotionDetail promotionDetailE) throws IOException;
}
