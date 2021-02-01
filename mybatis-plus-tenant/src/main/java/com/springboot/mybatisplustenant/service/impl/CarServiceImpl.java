package com.springboot.mybatisplustenant.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.mybatisplustenant.domain.Car;
import com.springboot.mybatisplustenant.mapper.CarMapper;
import com.springboot.mybatisplustenant.service.CarService;
/**
 * @author : yanwenhui
 * @description : 
 * @date : 2021/2/1
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService{

}
