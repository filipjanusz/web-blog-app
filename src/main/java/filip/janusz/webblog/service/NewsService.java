package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.News;
import filip.janusz.webblog.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    News createNews(NewsDTO newsDTO);
    News findNewsById(String id);
    List<News> findAllNews();
    Page<News> findAllNewsOrderByDateDesc(Pageable pageable);
    Page<News> searchFor(String searchContent, Pageable pageable);
}
