package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.ProductCategoridetailDTO;
import duong.dev.entity.ProductCategoridetail;

@Component
public class ProductCategoridetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	public ProductCategoridetail convertToEntity(ProductCategoridetailDTO dto) {
		ProductCategoridetail entity = new ProductCategoridetail();
		mapper.map(dto, entity);
		return entity;
	}
	
	public ProductCategoridetailDTO convertToDTO(ProductCategoridetail entity) {
		ProductCategoridetailDTO dto = new ProductCategoridetailDTO();
		mapper.map(entity, dto);
		return dto;
	}
}
