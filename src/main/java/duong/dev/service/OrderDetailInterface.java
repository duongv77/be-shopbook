package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.OrderDetailDTO;
import duong.dev.entity.OrderDetail;


public interface OrderDetailInterface {

	public List<OrderDetailDTO> readAll() throws IOException;
	
	public OrderDetailDTO create(OrderDetailDTO orderDetailD) throws IOException;
	
	public OrderDetailDTO update(OrderDetailDTO orderDetailD) throws IOException;
	
	public OrderDetailDTO delete(OrderDetail orderDetailE) throws IOException ;
}
