package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.CartdetailDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.request.UpdateQuantityCartdetailDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Cart;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;
import duong.dev.exception.AppException;
import duong.dev.mapper.AccountMapper;
import duong.dev.mapper.CartdetailMapper;
import duong.dev.repository.CartRepository;
import duong.dev.repository.CartdetailRepository;
import duong.dev.service.CartdetailInterface;

@Service
public class CartdetailServiceImpl implements CartdetailInterface{
	@Autowired JwtTokenUtil jwtToken;
	@Autowired AccountMapper accountMapper;
	@Autowired CartRepository cartRepo;
	@Autowired private CartdetailRepository cartdetailRepo;
	@Autowired private CartdetailMapper cartdetailMapper;
	
	@Override
	public CartdetailDTO create(Product product) throws IOException, ServletException {
		Account account = accountMapper.convertToEntity(jwtToken.getUserToToken());
		Cart cart = this.cartRepo.findByIdAccount(account.getId());
		
		if(cartdetailRepo.findCartDetailByProductAndCart(product.getId(), cart.getId()) ==null) {
			Cartdetail cartdetail = new Cartdetail();
			cartdetail.setCart(cart);
			cartdetail.setProduct(product);
			cartdetail.setQuantity(1);
			this.cartdetailRepo.save(cartdetail);
			return cartdetailMapper.convertToDTO(cartdetail);
		}else {
			Cartdetail cartdetail = cartdetailRepo.findCartDetailByProductAndCart(product.getId(), cart.getId());
			cartdetail.setQuantity(cartdetail.getQuantity() +1);
			this.cartdetailRepo.save(cartdetail);
			return cartdetailMapper.convertToDTO(cartdetail);
		}
	}
	
	@Override
	public CartdetailDTO update(CartdetailDTO cartDetaiDTO){
		cartdetailRepo.save(cartdetailMapper.convertToEntity(cartDetaiDTO));
		return cartDetaiDTO;
	}

	@Override
	public CartdetailDTO delete(Cartdetail cartdetailE){
		cartdetailRepo.delete(cartdetailE);
		return cartdetailMapper.convertToDTO(cartdetailE);
	}
	
	public List<CartdetailDTO> convertListDTO(List<Cartdetail> listcartdetailE) {
		List<CartdetailDTO> listcartdetailD = new ArrayList<CartdetailDTO>();
		for (Cartdetail cartdetailE : listcartdetailE) {
			listcartdetailD.add(cartdetailMapper.convertToDTO(cartdetailE));
		}
		return listcartdetailD;
	}
	
	public List<Cartdetail> convertListEntity(List<CartdetailDTO> listcartdetailDTO) {
		List<Cartdetail> listcartdetailE = new ArrayList<Cartdetail>();
		for (CartdetailDTO cartdetailD : listcartdetailDTO) {
			listcartdetailE.add(cartdetailMapper.convertToEntity(cartdetailD));
		}
		return listcartdetailE;
	}

	@Override
	public List<CartdetailDTO> readAll() throws IOException, ServletException {
		Cart cart = getCartByAccount();
		List<Cartdetail> list = cartdetailRepo.findCartDetailByIdCart(cart.getId());
		return convertListDTO(list);
	}
	
	public CartdetailDTO updateQuantity(UpdateQuantityCartdetailDTO updateQuantityCartdetailDTO) {
		Optional<Cartdetail> cartdetail = cartdetailRepo.findById(updateQuantityCartdetailDTO.getId());
		if(!cartdetail.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Cartdetail không tồn tại!");
		Cartdetail cartdetail2 = cartdetail.get();
		cartdetail2.setQuantity(updateQuantityCartdetailDTO.getQuantity());
		cartdetailRepo.save(cartdetail2);
		return cartdetailMapper.convertToDTO(cartdetail2);
	}
	
	public List<CartdetailDTO> deleteAllCartdetailByCart() throws IOException, ServletException {
		Cart cart = getCartByAccount();
		List<Cartdetail> listCartdetail = cartdetailRepo.findByCart(cart);
		cartdetailRepo.deleteAll(listCartdetail);
		return null;
	}
	
	public Cart getCartByAccount() throws IOException, ServletException{
		Account account = accountMapper.convertToEntity(jwtToken.getUserToToken());
		Cart cart = this.cartRepo.findByIdAccount(account.getId());
		return cart;
	}

}
