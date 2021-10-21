package duong.dev.service;

import java.util.List;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;


public interface ProductInterface {

	public List<ProductDTO> readAll();
	
	public ProductDTO create(ProductDTO productD,  Categorie categorie);
	
	public ProductDTO update(ProductDTO productD);
	
	public ProductDTO delete(Product productE);
}
