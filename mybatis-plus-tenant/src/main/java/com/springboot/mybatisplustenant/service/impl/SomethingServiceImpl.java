package com.springboot.mybatisplustenant.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.mybatisplustenant.domain.Something;
import com.springboot.mybatisplustenant.mapper.SomethingMapper;
import com.springboot.mybatisplustenant.service.SomethingService;
/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/2
 */
@Service
public class SomethingServiceImpl extends ServiceImpl<SomethingMapper, Something> implements SomethingService{


    @Override
    public List<Something> getAll() {
        return baseMapper.getAll();
    }
}
