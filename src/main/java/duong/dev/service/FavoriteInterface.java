package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.FavoriteDTO;
import duong.dev.entity.Favorite;


public interface FavoriteInterface {

	public List<FavoriteDTO> readAll() throws IOException;
	
	public FavoriteDTO create(FavoriteDTO favoriteD) throws IOException;
	
	public FavoriteDTO delete(Favorite favoriteE) throws IOException ;
}
