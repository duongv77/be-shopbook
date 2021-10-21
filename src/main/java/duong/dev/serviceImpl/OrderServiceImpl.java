package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.CartdetailDTO;
import duong.dev.dto.OrderDTO;
import duong.dev.dto.request.CreateOrderDTO;
import duong.dev.dto.request.UpdateStatusVsAddressOrderDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Order;
import duong.dev.entity.OrderDetail;
import duong.dev.entity.Product;
import duong.dev.exception.AppException;
import duong.dev.mapper.OrderMapper;
import duong.dev.repository.AccountRepository;
import duong.dev.repository.CartdetailRepository;
import duong.dev.repository.OrderDetailRepository;
import duong.dev.repository.OrderRepository;
import duong.dev.repository.ProductRepository;
import duong.dev.service.OrderInterface;

@Service
public class OrderServiceImpl implements  OrderInterface {

	@Autowired private OrderRepository orderRepo;
	@Autowired private OrderMapper orderMapper;
	@Autowired private ProductRepository productRepository;
	@Autowired private OrderDetailRepository orderDetailRepository;
	@Autowired private AccountRepository accountRepository;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private CartdetailServiceImpl cartdetailServiceImpl;
	@Autowired private CartdetailRepository cartdetailRepository;
	
	public List<OrderDTO> convertListDTO(List<Order> listOrderE) {
		List<OrderDTO> listOrderD = new ArrayList<OrderDTO>();
		for (Order orderE : listOrderE) {
			listOrderD.add(orderMapper.convertToDTO(orderE));
		}
		return listOrderD;
	}

	@Override
	public List<OrderDTO> readAll() throws IOException {
		List<Order> listOrderE = orderRepo.findAll();
		return convertListDTO(listOrderE);

	}
	
	@Override
	public Order create(String address) throws IOException, ServletException {
		AccountDTO accountDTO = jwtTokenUtil.getUserToToken();
		Account account = accountRepository.findById(accountDTO.getId()).get();
		Order order = new Order();
		order.setAddress(address);
		order.setAccount(account);
		order.setStatus(Status.STATUS_ORDER_CHO_XAC_NHAN);
		order.setTypeorder(Status.STATUS_TYPE_ORDER_CART);
		orderRepo.save(order);
		return order;
	}
	
//	Xử lí order
	public List<CartdetailDTO> createDetail(CreateOrderDTO createOrderDTO) throws IOException, ServletException {		
		Order order = create(createOrderDTO.getAddress());
		Integer total=0;
		for (CartdetailDTO cartdetailDTO : createOrderDTO.getCartdetail()) {
			Product product = productRepository.getById(cartdetailDTO.getProduct().getId());
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setProduct(product);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(cartdetailDTO.getQuantity());
			orderDetail.setStatus(Status.STATUS_HOAT_DONG);
			if(product.getPromotiondetail()==null) { //Không có chương trình giảm giá
				total += product.getPrice()*cartdetailDTO.getQuantity();
			}else { //có giảm giá
				int sale = product.getPromotiondetail().getPromotion().getSale();
				orderDetail.setSale(sale);
				total += product.getPrice()*cartdetailDTO.getQuantity()/100*(100-sale);
			}
			orderDetailRepository.save(orderDetail);
		}
		order.setTotal(total);
		orderRepo.save(order);
		
		List<Cartdetail> listCartDetail = cartdetailServiceImpl.convertListEntity(createOrderDTO.getCartdetail());
		cartdetailRepository.deleteAll(listCartDetail);
		
		return cartdetailServiceImpl.readAll();
	}

	@Override
	public OrderDTO delete(Order orderE) throws IOException {
		orderRepo.delete(orderE);
		return orderMapper.convertToDTO(orderE);
	}
	
	@Override
	public OrderDTO update(OrderDTO orderD) throws IOException {
		return null;
	}
	
	public List<OrderDTO> getListOrderByStatus(Integer status) {
		List<Order> listOrderE = orderRepo.findByStatus(status);
		return convertListDTO(listOrderE);
	}

	public OrderDTO updateStatusVsAddress(UpdateStatusVsAddressOrderDTO orderDTO) {
		Order order = orderRepo.findById(orderDTO.getId()).get();
		if(order==null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		order.setStatus(orderDTO.getStatus());
		order.setAddress(orderDTO.getAddress());
		orderRepo.save(order);
		return orderMapper.convertToDTO(order);
	}
}
