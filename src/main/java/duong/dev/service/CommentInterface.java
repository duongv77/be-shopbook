package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.CommentDTO;
import duong.dev.entity.Comment;


public interface CommentInterface {

	public List<CommentDTO> readAll() throws IOException;
	
	public CommentDTO create(CommentDTO commentD) throws IOException;
	
	public CommentDTO update(CommentDTO commentD) throws IOException;
	
	public CommentDTO delete(Comment commentE) throws IOException ;
}
