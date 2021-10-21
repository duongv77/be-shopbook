package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.OrderDetailDTO;
import duong.dev.entity.OrderDetail;
import duong.dev.mapper.OrderDetailMapper;
import duong.dev.repository.OrderDetailRepository;
import duong.dev.service.OrderDetailInterface;

@Service
public class OrderDetailServiceImpl implements  OrderDetailInterface {

	@Autowired
	private OrderDetailRepository orderDetailRepo;
	@Autowired
	OrderDetailMapper orderDetailMapper;

	public List<OrderDetailDTO> convertListDTO(List<OrderDetail> listOrderDetailE) {
		List<OrderDetailDTO> listOrderDetailD = new ArrayList<OrderDetailDTO>();
		for (OrderDetail orderDetailE : listOrderDetailE) {
			listOrderDetailD.add(orderDetailMapper.convertToDTO(orderDetailE));
		}
		return listOrderDetailD;
	}

	@Override
	public List<OrderDetailDTO> readAll() throws IOException {
		List<OrderDetail> listOrderDetailE = orderDetailRepo.findAll();
		return convertListDTO(listOrderDetailE);

	}

	@Override
	public OrderDetailDTO create(OrderDetailDTO orderDetailD) throws IOException {		
		OrderDetail orderDetailE = orderDetailMapper.convertToEntity(orderDetailD);
		orderDetailRepo.save(orderDetailE);
		return orderDetailMapper.convertToDTO(orderDetailE);
		
	}

	@Override
	public OrderDetailDTO delete(OrderDetail orderDetailE) throws IOException {
		orderDetailRepo.delete(orderDetailE);
		return orderDetailMapper.convertToDTO(orderDetailE);
	}
	
	@Override
	public OrderDetailDTO update(OrderDetailDTO orderD) throws IOException {
		
		return null;
	}

}
