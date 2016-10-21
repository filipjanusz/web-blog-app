package filip.janusz.webblog.repo;

import filip.janusz.webblog.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepo extends MongoRepository<News, String> {
    Page<News> findBy(Pageable pageable, TextCriteria textCriteria);
}
