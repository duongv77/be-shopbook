package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.CategorieDTO;
import duong.dev.dto.ProductCategoridetailDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;
import duong.dev.entity.ProductCategoridetail;
import duong.dev.mapper.CategorieMapper;
import duong.dev.mapper.ProductCategoridetailMapper;
import duong.dev.repository.ProductCategoridetailRepository;
import duong.dev.service.ProductCategoridetailInterface;

@Component
public class ProductCategoridetailServiceImpl implements ProductCategoridetailInterface{
	@Autowired private ProductCategoridetailRepository productCategoridetailRepo;
	@Autowired private ProductCategoridetailMapper proCateMapper;
	@Autowired private CategorieServiceImpl categorieServiceImpl;
	
	@Override
	public ProductCategoridetail create(ProductCategoridetail productCategoridetail) {
		productCategoridetailRepo.save(productCategoridetail);
		return productCategoridetail;
	}
	
	public List<ProductCategoridetailDTO> convertListDTO(List<ProductCategoridetail> productCategoridetails){
		List<ProductCategoridetailDTO> productCategoridetailDTOs = new ArrayList<ProductCategoridetailDTO>();
		for (ProductCategoridetail productCategoridetai : productCategoridetails) {
			productCategoridetailDTOs.add(proCateMapper.convertToDTO(productCategoridetai));
		}
		return productCategoridetailDTOs;
	}
	
//	xóa all category của 1 product
	public void deleteAll(Product product) {
		List<ProductCategoridetail> productCategoridetails = productCategoridetailRepo.findByProduct(product);
		productCategoridetailRepo.deleteAll(productCategoridetails);
	}
	
	//tạo danh mục cho 1 product
	public void createAll(Product product, List<CategorieDTO> listCategorieDTO) {
		List<Categorie> listCategorie = categorieServiceImpl.convertListE(listCategorieDTO);
		for (Categorie categorie : listCategorie) {
			ProductCategoridetail productCategoridetail = new ProductCategoridetail();
			productCategoridetail.setCategorie(categorie);
			productCategoridetail.setProduct(product);
			productCategoridetailRepo.save(productCategoridetail);
		}
		
	}

}
