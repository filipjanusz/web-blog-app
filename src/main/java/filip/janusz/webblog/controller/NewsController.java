package filip.janusz.webblog.controller;

import filip.janusz.webblog.domain.Comment;
import filip.janusz.webblog.domain.News;
import filip.janusz.webblog.domain.SearchContent;
import filip.janusz.webblog.dto.CommentDTO;
import filip.janusz.webblog.dto.NewsDTO;
import filip.janusz.webblog.pagination.PageWrapper;
import filip.janusz.webblog.service.CommentService;
import filip.janusz.webblog.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

    private NewsService newsService;
    private CommentService commentService;

    @Autowired
    public NewsController(NewsService newsService, CommentService commentService) {
        this.newsService = newsService;
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allNews")
    public String showAllNews(Model model,
                              Pageable pageable,
                              @RequestParam(value = "search", required = false) String search) {
        PageWrapper<News> pageWrapper;
        if(search != null) {
            pageWrapper = new PageWrapper<>(newsService.searchFor(search, pageable), "/news/allNews?search=" + search);
        } else {
            pageWrapper = new PageWrapper<>(newsService.findAllNewsOrderByDateDesc(pageable), "/news/allNews");
        }
        model.addAttribute("page", pageWrapper);
        model.addAttribute("newsList", pageWrapper.getContent());
        model.addAttribute("search", new SearchContent());
        return "allNews";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/allNews/search")
    public String search(@ModelAttribute("search") SearchContent search) {
        return "redirect:/news/allNews?search=" + search.getContent();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addNews")
    public String addNewsView(Model model) {
        model.addAttribute("news", new NewsDTO());
        return "addNews";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addNews")
    public String addNews(@Valid @ModelAttribute("news") NewsDTO newsDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "addNews";
        }
        newsService.createNews(newsDTO);
        return "redirect:/news/allNews";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{newsId}")
    public String showNews(@PathVariable("newsId") String newsId, Model model) {
        News news = newsService.findNewsById(newsId);
        List<Comment> commentList = commentService.findCommentsByNewsId(newsId);
        model.addAttribute("news", news);
        model.addAttribute("commentList", commentList);
        model.addAttribute("comment", new CommentDTO());
        return "news";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{newsId}")
    public String addComment(@PathVariable("newsId") String newsId,
                             @ModelAttribute("comment") CommentDTO commentDTO) {
        commentDTO.setNewsId(newsId);
        commentService.createComment(commentDTO);
        return "redirect:/news/" + newsId;
    }
}
