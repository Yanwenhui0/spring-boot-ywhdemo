package com.springboot.myweb.controller;

import com.springboot.myweb.domain.Article;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/11
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    /**
     * GET 请求：127.0.0.1:8080/ywh/article?id=2&title=ywh&author=yanwenhui&content=xxxxx
     * 进入控制器前 @ModelAttribute 会处理参数封装成Article
     */
    @GetMapping
    public Article getArticle(@ModelAttribute Article article) {
        return article;
    }

    /**
     * POST: @RequestParam (不传 => 400)
     * 1/ params => OK
     * 2/ form-data => OK
     * 3/ x-www-form-urlencoded => OK
     * 4/ json => null(传不到后端)
     */
    @PostMapping("/request-param")
    public Article formDate(@RequestParam Integer id,
                            @RequestParam String title,
                            @RequestParam String author,
                            @RequestParam String content) {
        return new Article(id, title, author, content);
    }

    /**
     * POST:
     * 1/ params => OK
     * 2/ form-data => OK
     * 3/ x-www-form-urlencoded => OK
     * 4/ json => null(传不到后端)
     */
    @PostMapping("/entity")
    public Article formDate(Article article) {
        return article;
    }

    /**
     * POST: @RequestBody (不传'{}' => 400)
     * 1/ params => 传不到后端，400
     * 2/ form-data => 415
     * 3/ x-www-form-urlencoded => 415
     * 4/ json => OK
     * 总结：@RequestBody 只能获取到json类型
     */
    @PostMapping(value = "/request-body", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Article json(@RequestBody Article article) {
        return article;
    }


}
