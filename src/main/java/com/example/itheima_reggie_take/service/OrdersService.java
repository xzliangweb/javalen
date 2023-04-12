package com.example.itheima_reggie_take.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.itheima_reggie_take.entity.Orders;

public interface OrdersService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
}
