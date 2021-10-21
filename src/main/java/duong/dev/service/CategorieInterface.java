package duong.dev.service;

import java.io.IOException;

import duong.dev.dto.CategorieDTO;
import duong.dev.entity.Categorie;

public interface CategorieInterface {
	public CategorieDTO create(CategorieDTO categorieD) throws IOException;
	
	public CategorieDTO update(CategorieDTO categorieD) throws IOException;
	
	public CategorieDTO delete(Categorie categorieE) throws IOException;
}
