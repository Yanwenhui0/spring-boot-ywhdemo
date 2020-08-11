package com.springboot.myweb.service.impl;

import com.springboot.myweb.domain.Article;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.myweb.mapper.ArticleMapper;
import com.springboot.myweb.service.ArticleService;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/11
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}



