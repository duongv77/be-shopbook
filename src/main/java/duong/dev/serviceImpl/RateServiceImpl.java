package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.RateDTO;
import duong.dev.entity.Rate;
import duong.dev.mapper.RateMapper;
import duong.dev.repository.RateRepository;
import duong.dev.service.RateInterface;

@Service
public class RateServiceImpl implements RateInterface {
	@Autowired
	RateRepository rateRepo;
	@Autowired
	RateMapper rateMapper;
	
	public List<RateDTO> convertListDTO(List<Rate> listRateE ) {
		List<RateDTO> listRateD = new ArrayList<RateDTO>();
		for(Rate rateE : listRateE ) {
			listRateD.add(rateMapper.convertToDTO(rateE));
		}
		return listRateD;
	}

	@Override
	public List<RateDTO> readAll() throws IOException {
		return convertListDTO(rateRepo.findAll());
	}
	

}
