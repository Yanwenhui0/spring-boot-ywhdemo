package com.springboot.springsecurity.resource.controller;

import com.springboot.springsecurity.resource.domain.TbContent;
import com.springboot.springsecurity.resource.dto.ResponseResult;
import com.springboot.springsecurity.resource.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 @description :
 @author : yanwenhui
 @date : 2020/8/6
 */
@RestController
public class TbContentController {

    private static final String ROLE_ADMIN = "hasRole('admin')";

    @Autowired
    private TbContentService tbContentService;

    /**
     * 获取全部资源
     *
     * @return
     */
    @GetMapping("/no")
    public ResponseResult<List<TbContent>> selectAllWithout() {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), tbContentService.selectAll());
    }

    /**
     * 获取全部资源
     *
     * @return
     */
    @PreAuthorize(ROLE_ADMIN)
    @GetMapping("/")
    public ResponseResult<List<TbContent>> selectAll() {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), tbContentService.selectAll());
    }

    /**
     * 获取资源详情
     *
     * @param id
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/view/{id}")
    public ResponseResult<TbContent> getById(@PathVariable Long id) {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), tbContentService.getById(id));
    }

    /**
     * 新增资源
     *
     * @param tbContent
     * @return
     */
    @PostMapping("/insert")
    public ResponseResult<Integer> insert(@RequestBody TbContent tbContent) {
        int count = tbContentService.insert(tbContent);

        if (count > 0) {
            return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), count);
        } else {
            return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        }
    }

    /**
     * 更新资源
     *
     * @param tbContent
     * @return
     */
    @PutMapping("/update")
    public ResponseResult<Integer> update(@RequestBody TbContent tbContent) {
        int count = tbContentService.update(tbContent);

        if (count > 0) {
            return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), count);
        } else {
            return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult<Integer> delete(@PathVariable Long id) {
        int count = tbContentService.delete(id);

        if (count > 0) {
            return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), count);
        } else {
            return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        }
    }
}
