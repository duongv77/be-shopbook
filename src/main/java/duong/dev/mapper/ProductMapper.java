package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Product;



@Component
public class ProductMapper {

	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Product convertToEntity(ProductDTO productD) {
		Product entity = new Product();
		mapper.map(productD, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public ProductDTO convertToDTO(Product entity) {
		ProductDTO productDTO = new ProductDTO();
		mapper.map(entity, productDTO);
		return productDTO;
	}
}
