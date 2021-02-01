package com.springboot.mybatisplustenant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.mybatisplustenant.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    public void saveBook() {
        bookService.save(new Book().setBookName("第一本"));
        bookService.save(new Book().setBookName("第二本"));
        bookService.save(new Book().setBookName("第三本"));
    }

    @Test
    public void getBook() {
        System.out.println(bookService.getOne(new QueryWrapper<>(new Book().setBookName("第一本"))));
    }

    @Test
    public void listBook() {
        for (Book book : bookService.list(new QueryWrapper<>(new Book().setBookName("第一本")))) {
            System.out.println(book);
        }
    }
}
