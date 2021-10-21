package duong.dev.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import duong.dev.dto.OrderDTO;
import duong.dev.dto.request.CreateOrderDTO;
import duong.dev.entity.Order;


public interface OrderInterface {

	public List<OrderDTO> readAll() throws IOException;
	
	public Order create(String address) throws IOException, ServletException;
	
	public OrderDTO update(OrderDTO orderD) throws IOException;
	
	public OrderDTO delete(Order orderE) throws IOException ;
}
