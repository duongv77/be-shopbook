package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.CommentDTO;
import duong.dev.entity.Comment;
import duong.dev.mapper.CommentMapper;
import duong.dev.repository.CommentRepository;
import duong.dev.service.CommentInterface;

@Service
public class CommentServiceImpl implements CommentInterface {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	CommentMapper commentMapper;

	public List<CommentDTO> convertListDTO(List<Comment> listCommentsE) {
		List<CommentDTO> listCommentD = new ArrayList<CommentDTO>();
		for (Comment commentsE : listCommentsE) {
			listCommentD.add(commentMapper.convertToDTO(commentsE));
		}
		return listCommentD;
	}

	@Override
	public List<CommentDTO> readAll() throws IOException {
		List<Comment> listCommentE = commentRepo.findAll();
		return convertListDTO(listCommentE);

	}

	@Override
	public CommentDTO create(CommentDTO commentD) throws IOException {		
		Comment commentE = commentMapper.convertToEntity(commentD);
		commentRepo.save(commentE);
		return commentMapper.convertToDTO(commentE);
		
	}

	@Override
	public CommentDTO update(CommentDTO commentD) throws IOException {
		
		return null;

	}

	@Override
	public CommentDTO delete(Comment commentE) throws IOException {
		commentRepo.delete(commentE);
		return commentMapper.convertToDTO(commentE);
	}

}
