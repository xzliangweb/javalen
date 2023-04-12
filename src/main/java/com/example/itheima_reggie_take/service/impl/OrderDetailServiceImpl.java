package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.entity.OrderDetail;
import com.example.itheima_reggie_take.mapper.OrderDetailMapper;
import com.example.itheima_reggie_take.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
