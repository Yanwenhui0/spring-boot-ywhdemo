package com.springboot.myweb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.myweb.domain.Article;
import com.springboot.myweb.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/11
 */
@SpringBootTest
public class AutoFillTest {

    @Resource
    ArticleMapper articleMapper;

    @Test
    public void autoFillInsert() {
        Article article = new Article();
        article.setTitle("1");
        article.setAuthor("1");
        article.setContent("1");

        articleMapper.insert(article);

    }

    @Test
    public void autoFillUpdate() {

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "1");
        Article article = articleMapper.selectOne(queryWrapper);
        article.setContent("1");
        articleMapper.updateById(article);

    }

    @Test
    public void logicDelete() {
        System.out.println(articleMapper.deleteById(10));
    }

    @Test
    public void selectAll() {
        List<Article> articles = articleMapper.selectList(null);
        articles.forEach(article -> System.out.println(article.toString()));
    }
}
