package duong.dev.service;

import java.io.IOException;
import java.util.List;


import duong.dev.dto.RateDTO;

public interface RateInterface {
	public List<RateDTO> readAll() throws IOException;
}
