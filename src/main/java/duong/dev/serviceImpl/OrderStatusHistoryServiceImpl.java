package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.OrderStatusHistoryDTO;
import duong.dev.entity.OrderStatusHistory;
import duong.dev.mapper.OrderStatusHistoryMapper;
import duong.dev.repository.OrderStatusHistoryRepository;
import duong.dev.service.OrderStatusHistoryInterface;





@Service
public class OrderStatusHistoryServiceImpl implements  OrderStatusHistoryInterface {

	@Autowired
	private OrderStatusHistoryRepository orderStatusHistoryRepo;
	@Autowired
	OrderStatusHistoryMapper OrderStatusHistoryMapper;

	public List<OrderStatusHistoryDTO> convertListDTO(List<OrderStatusHistory> listOrderStatusHistoryE) {
		List<OrderStatusHistoryDTO> listOrderStatusHistoryD = new ArrayList<OrderStatusHistoryDTO>();
		for (OrderStatusHistory orderStatusHistoryE : listOrderStatusHistoryE) {
			listOrderStatusHistoryD.add(OrderStatusHistoryMapper.convertToDTO(orderStatusHistoryE));
		}
		return listOrderStatusHistoryD;
	}

	@Override
	public List<OrderStatusHistoryDTO> readAll() throws IOException {
		List<OrderStatusHistory> listOrderStatusHistoryE = orderStatusHistoryRepo.findAll();
		return convertListDTO(listOrderStatusHistoryE);

	}

	@Override
	public OrderStatusHistoryDTO create(OrderStatusHistoryDTO orderStatusHistoryD) throws IOException {		
		OrderStatusHistory orderStatusHistoryE = OrderStatusHistoryMapper.convertToEntity(orderStatusHistoryD);
		orderStatusHistoryRepo.save(orderStatusHistoryE);
		return OrderStatusHistoryMapper.convertToDTO(orderStatusHistoryE);
		
	}

	@Override
	public OrderStatusHistoryDTO delete(OrderStatusHistory orderStatusHistoryE) throws IOException {
		orderStatusHistoryRepo.delete(orderStatusHistoryE);
		return OrderStatusHistoryMapper.convertToDTO(orderStatusHistoryE);
	}
	
	@Override
	public OrderStatusHistoryDTO update(OrderStatusHistoryDTO orderStatusHistoryD) throws IOException {
		
		return null;
	}

}
