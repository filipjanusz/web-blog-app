package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.Comment;
import filip.janusz.webblog.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO commentDTO);
    List<Comment> findCommentsByNewsId(String newsId);
}
