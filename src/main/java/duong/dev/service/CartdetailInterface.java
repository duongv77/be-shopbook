package duong.dev.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;


import duong.dev.dto.CartdetailDTO;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;

public interface CartdetailInterface {
	
	public List<CartdetailDTO> readAll() throws IOException, ServletException;
	
	public CartdetailDTO create(Product product) throws IOException, ServletException;
	
	public CartdetailDTO update(CartdetailDTO cartDetaiDTO);
	
	public CartdetailDTO delete(Cartdetail cartdetailE);
}
