package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.entity.DishFlavor;
import com.example.itheima_reggie_take.mapper.DishFlavorMapper;
import com.example.itheima_reggie_take.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
