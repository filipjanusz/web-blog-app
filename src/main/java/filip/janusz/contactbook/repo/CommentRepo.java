package filip.janusz.contactbook.repo;

import filip.janusz.contactbook.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends MongoRepository<Comment, String> {
    List<Comment> findByNewsId(String newsId);
}
