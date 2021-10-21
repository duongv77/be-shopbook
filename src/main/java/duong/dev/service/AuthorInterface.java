package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.AuthorDTO;
import duong.dev.entity.Author;

public interface AuthorInterface {
	public List<AuthorDTO> readAll() throws IOException;
	
	public AuthorDTO create(AuthorDTO authorD) throws IOException;
	
	public AuthorDTO update(AuthorDTO authorD) throws IOException;
	
	public AuthorDTO delete(Author authorE) throws IOException;
}
