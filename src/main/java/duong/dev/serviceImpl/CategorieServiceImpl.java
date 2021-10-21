package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.common.Common;
import duong.dev.dto.CategorieDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;
import duong.dev.entity.ProductCategoridetail;
import duong.dev.mapper.CategorieMapper;
import duong.dev.repository.CategorieRepository;
import duong.dev.repository.ProductCategoridetailRepository;
import duong.dev.service.CategorieInterface;

@Service
public class CategorieServiceImpl implements CategorieInterface{
	@Autowired private CategorieRepository cateRepo;
	@Autowired private CategorieMapper cateMapper;
	@Autowired private ProductCategoridetailRepository proCateRepo;

	public List<CategorieDTO> readCateoryBig() {
		return convertListDTO(cateRepo.findCategoryBig(Common.ID_CATEGORY_BIG));
	}
	
	public List<CategorieDTO> readCateoryByCateGoryId(Integer id){
		return convertListDTO(cateRepo.findCategoryBig(id));
	}

	@Override
	public CategorieDTO create(CategorieDTO categorieD) throws IOException {
		Categorie cateE = cateMapper.convertToEntity(categorieD);
		cateRepo.save(cateE);
		return cateMapper.convertToDTO(cateE);
	}
	
	@Override
	public CategorieDTO update(CategorieDTO categorieD) throws IOException {
		cateRepo.save(cateMapper.convertToEntity(categorieD));
		return categorieD;
	}

	@Override
	public CategorieDTO delete(Categorie categorieE) throws IOException {
		cateRepo.delete(categorieE);
		return cateMapper.convertToDTO(categorieE);
	}
	
	
	public List<CategorieDTO> convertListDTO(List<Categorie> listCategorieE) {
		List<CategorieDTO> listCategorieD = new ArrayList<CategorieDTO>();
		for (Categorie categorieE : listCategorieE) {
			listCategorieD.add(cateMapper.convertToDTO(categorieE));
		}
		return listCategorieD;
	}
	
	public List<Categorie> convertListE(List<CategorieDTO> listCategorieDTO) {
		List<Categorie> listCategorieE = new ArrayList<Categorie>();
		for (CategorieDTO categorieD : listCategorieDTO) {
			listCategorieE.add(cateMapper.convertToEntity(categorieD));
		}
		return listCategorieE;
	}

	public List<CategorieDTO> finByListCateToProduct(Product product) {
		List<ProductCategoridetail> listProductCategoridetails = proCateRepo.findByProduct(product);
		List<Categorie> listCate = new ArrayList<Categorie>();
		for (ProductCategoridetail productCategoridetail : listProductCategoridetails) {
			listCate.add(productCategoridetail.getCategorie());
		}
		return convertListDTO(listCate);
	}

}
