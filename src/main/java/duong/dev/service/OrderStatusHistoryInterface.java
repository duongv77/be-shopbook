package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.OrderStatusHistoryDTO;
import duong.dev.entity.OrderStatusHistory;


public interface OrderStatusHistoryInterface {

	public List<OrderStatusHistoryDTO> readAll() throws IOException;
	
	public OrderStatusHistoryDTO create(OrderStatusHistoryDTO orderStatusHistoryD) throws IOException;
	
	public OrderStatusHistoryDTO update(OrderStatusHistoryDTO orderStatusHistoryD) throws IOException;
	
	public OrderStatusHistoryDTO delete(OrderStatusHistory orderStatusHistoryE) throws IOException ;
}
