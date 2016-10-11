package filip.janusz.contactbook.repo;

import filip.janusz.contactbook.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepo extends MongoRepository<News, String> {
    Page<News> findBy(Pageable pageable, TextCriteria textCriteria);
}
