package pub.tam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pub.tam.blog.model.Article;
import pub.tam.blog.model.HttpResponse;
import pub.tam.blog.repository.ArticleRepository;
import pub.tam.blog.service.impl.ArticleServiceImpl;

import java.util.List;

@RestController
public class ArticleController {
    private ArticleServiceImpl articleService;

    @Autowired
    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public Page<Article> findAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Article> articlesPage = articleService.findAll(pageable);
        return articlesPage;
    }
}
