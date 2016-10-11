package filip.janusz.contactbook.service;

import filip.janusz.contactbook.domain.News;
import filip.janusz.contactbook.dto.NewsDTO;
import filip.janusz.contactbook.pagination.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;

import java.util.List;

public interface NewsService {
    News createNews(NewsDTO newsDTO);
    News findNewsById(String id);
    List<News> findAllNews();
    Page<News> findAllNewsOrderByDateDesc(Pageable pageable);
    Page<News> searchFor(String searchContent, Pageable pageable);
}
