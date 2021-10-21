package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.FavoriteDTO;
import duong.dev.entity.Favorite;
import duong.dev.mapper.FavoriteMapper;
import duong.dev.repository.FavoriteRepository;
import duong.dev.service.FavoriteInterface;


@Service
public class FavoriteServiceImpl implements  FavoriteInterface {

	@Autowired
	private FavoriteRepository favoriteRepo;
	@Autowired
	FavoriteMapper favoriteMapper;

	public List<FavoriteDTO> convertListDTO(List<Favorite> listFavoriteE) {
		List<FavoriteDTO> listFavoriteD = new ArrayList<FavoriteDTO>();
		for (Favorite favoriteE : listFavoriteE) {
			listFavoriteD.add(favoriteMapper.convertToDTO(favoriteE));
		}
		return listFavoriteD;
	}

	@Override
	public List<FavoriteDTO> readAll() throws IOException {
		List<Favorite> listFavoriteE = favoriteRepo.findAll();
		return convertListDTO(listFavoriteE);

	}

	@Override
	public FavoriteDTO create(FavoriteDTO favoriteD) throws IOException {		
		Favorite favoriteE = favoriteMapper.convertToEntity(favoriteD);
		favoriteRepo.save(favoriteE);
		return favoriteMapper.convertToDTO(favoriteE);
		
	}


	@Override
	public FavoriteDTO delete(Favorite favoriteE) throws IOException {
		favoriteRepo.delete(favoriteE);
		return favoriteMapper.convertToDTO(favoriteE);
	}

}
