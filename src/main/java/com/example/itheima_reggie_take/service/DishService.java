package com.example.itheima_reggie_take.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.itheima_reggie_take.dto.DishDto;
import com.example.itheima_reggie_take.entity.Dish;

public interface DishService extends IService<Dish> {
    // 新增菜品，同时插入菜品对应的口味数据，需要操作两张表dish 和 dish_flavor
    void saveWithFlavor(DishDto dishDto);

    // 根据id 查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息，同时更新口味信息
    void updateWithFlavor(DishDto dishDto);
    // 菜品管理的删除
    void remove(Long ids);
}
