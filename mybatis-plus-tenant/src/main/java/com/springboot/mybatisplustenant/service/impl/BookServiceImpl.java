package com.springboot.mybatisplustenant.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.mybatisplustenant.domain.Book;
import com.springboot.mybatisplustenant.mapper.BookMapper;
import com.springboot.mybatisplustenant.service.BookService;
/**
 * @author : yanwenhui
 * @description : 
 * @date : 2021/2/1
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService{

}
