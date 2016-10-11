package filip.janusz.contactbook.service;

import filip.janusz.contactbook.domain.Comment;
import filip.janusz.contactbook.domain.CurrentUser;
import filip.janusz.contactbook.dto.CommentDTO;
import filip.janusz.contactbook.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setNewsId(commentDTO.getNewsId());
        comment.setUsername(getCurrentUserUsername());
        comment.setDate(new Date());
        return commentRepo.save(comment);
    }

    private String getCurrentUserUsername() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUsername();
    }

    @Override
    public List<Comment> findCommentsByNewsId(String newsId) {
        return commentRepo.findByNewsId(newsId);
    }
}
