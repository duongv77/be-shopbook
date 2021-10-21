package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.CategorieDTO;
import duong.dev.dto.ProductCategoridetailDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;
import duong.dev.entity.ProductCategoridetail;
import duong.dev.exception.AppException;
import duong.dev.mapper.ProductCategoridetailMapper;
import duong.dev.mapper.ProductMapper;
import duong.dev.repository.ProductRepository;
import duong.dev.service.ProductInterface;

@Service
public class ProductServiceImpl implements ProductInterface {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductMapper productMapper;
	@Autowired 
	private ProductCategoridetailServiceImpl proCatedetailSvImpl;
	@Autowired private CategorieServiceImpl categorieServiceImpl;
	
	public ProductDTO getOnly(Integer id) {
		Optional<Product> product = productRepo.findById(id);
		if(!product.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");
		List<CategorieDTO> categorieDTOs = categorieServiceImpl.finByListCateToProduct(product.get());
		ProductDTO productDTO = productMapper.convertToDTO(product.get());
		productDTO.setCategories(categorieDTOs);
		return productDTO;
	}

	public List<ProductDTO> convertListDTO(List<Product> listProductE) {
		List<ProductDTO> listProductD = new ArrayList<ProductDTO>();
		for (Product productE : listProductE) {
			listProductD.add(productMapper.convertToDTO(productE));
		}
		return listProductD;
	}

	@Override
	public List<ProductDTO> readAll(){
		List<Product> listProductE = productRepo.findAll();
		return convertListDTO(listProductE);

	}

	@Override
	public ProductDTO create(ProductDTO productD, Categorie categorie){	
		Product productE = productMapper.convertToEntity(productD);
		productE.setAvailable(productE.getQuantity()>0? Status.STATUS_HOAT_DONG: Status.STATUS_KHONG_HOAT_DONG);
		productRepo.save(productE);
		
		ProductCategoridetail productCategoridetail = new ProductCategoridetail();
		productCategoridetail.setCategorie(categorie);
		productCategoridetail.setProduct(productE);
		
		proCatedetailSvImpl.create(productCategoridetail);
		
		return productMapper.convertToDTO(productE);
		
	}

	@Override
	public ProductDTO delete(Product productE){
		try {
			proCatedetailSvImpl.deleteAll(productE);
			productRepo.delete(productE);
			return productMapper.convertToDTO(productE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ProductDTO update(ProductDTO productD){
		List<CategorieDTO> listCategorieDTO = productD.getCategories();
		Product product = productMapper.convertToEntity(productD);
		if(product.getQuantity()<=0) {
			product.setAvailable(Status.STATUS_KHONG_HOAT_DONG);
		}else {
			product.setAvailable(Status.STATUS_HOAT_DONG);
		}
		productRepo.save(product);
		proCatedetailSvImpl.deleteAll(product);
		proCatedetailSvImpl.createAll(product, listCategorieDTO);
		return productMapper.convertToDTO(product);
	}

}
