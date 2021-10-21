package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.PromotionDTO;
import duong.dev.entity.Promotion;

public interface PromotionInterface {
	public List<PromotionDTO> readAll() throws IOException;
	
	public PromotionDTO create(PromotionDTO promotionD) throws IOException;
	
	public PromotionDTO update(PromotionDTO promotionD) throws IOException;
	
	public PromotionDTO delete(Promotion promotionE) throws IOException;
}
