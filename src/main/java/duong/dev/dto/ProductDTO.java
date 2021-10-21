package duong.dev.dto;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductDTO {
	private Integer id;
	
	@NotBlank(message = "Không được để trống tên")
	private String name;
	
	@NotNull(message = "Không được để trống giá")
	private Integer price;
	
	private Integer available;
	
	private String description;
	
	private String image;
	
	@NotNull(message = "Không được để trống số lượng")
	private Integer quantity;
	
	private Integer numberpages;
	
	private String form;
	
	private Integer publishyear;
	
	private String publisher;
	
	private String supplier;
	
	private String language;
	
	private AuthorDTO author;
	
	private RegionDTO region;
	
	private TitleDTO title;
	
	private List<ProductCategoridetailDTO> productCategoridetail;
	
	private List<CategorieDTO> categories;
	
	private PromotionDetailDTO promotiondetail;
}
