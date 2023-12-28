package pub.tam.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pub.tam.blog.model.Article;

import java.util.List;

public interface ArticleService {

    Page<Article> findAll(Pageable pageable);
}
