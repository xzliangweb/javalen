package com.example.itheima_reggie_take.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.itheima_reggie_take.common.R;
import com.example.itheima_reggie_take.entity.Orders;
import com.example.itheima_reggie_take.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Orders::getOrderTime);
        ordersService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 去支付获取订单。
     *
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return R.success("success");
    }
}
