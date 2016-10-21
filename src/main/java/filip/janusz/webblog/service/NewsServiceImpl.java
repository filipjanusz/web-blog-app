package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.CurrentUser;
import filip.janusz.webblog.domain.News;
import filip.janusz.webblog.dto.NewsDTO;
import filip.janusz.webblog.repo.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    MongoTemplate mongoTemplate;

    private NewsRepo newsRepo;

    @Autowired
    public NewsServiceImpl(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override
    public News createNews(NewsDTO newsDTO) {
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setDescription(newsDTO.getDescription());
        news.setUserId(getCurrentUserId());
        news.setUsername(getCurrentUserUsername());
        news.setDate(new Date());
        return newsRepo.save(news);
    }

    private String getCurrentUserId() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getId();
    }

    private String getCurrentUserUsername() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getUsername();
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //return authentication.getName();
    }

    @Override
    public News findNewsById(String id) {
        return newsRepo.findOne(id);
    }

    @Override
    public List<News> findAllNews() {
        return newsRepo.findAll(new Sort(Sort.Direction.DESC, "date"));
    }

    @Override
    public Page<News> findAllNewsOrderByDateDesc(Pageable pageable) {
        final PageRequest pageRequest = new PageRequest(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                new Sort(new Sort.Order(Sort.Direction.DESC, "date")));
        return newsRepo.findAll(pageRequest);
    }

    @Override
    public Page<News> searchFor(String searchContent, Pageable pageable) {
        TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .onField("title", 2F)
                .onField("username")
                .build();
        mongoTemplate.indexOps(News.class).ensureIndex(textIndex);

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchContent);

        //Query query = TextQuery.queryText(textCriteria);

        return newsRepo.findBy(pageable, textCriteria);
    }
}
