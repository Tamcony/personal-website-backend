package pub.tam.blog.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pub.tam.blog.model.Article;
import pub.tam.blog.repository.ArticleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void findAll() {
        List<Article> articleList = articleRepository.findAll();
        if(articleList != null)
        System.out.println("articleList.size:"+articleList.size());
    }
}