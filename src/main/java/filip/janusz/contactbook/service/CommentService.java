package filip.janusz.contactbook.service;

import filip.janusz.contactbook.domain.Comment;
import filip.janusz.contactbook.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO commentDTO);
    List<Comment> findCommentsByNewsId(String newsId);
}
