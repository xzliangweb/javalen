package com.example.itheima_reggie_take.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.itheima_reggie_take.dto.SetmealDto;
import com.example.itheima_reggie_take.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同事需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    void removeWithDish(List<Long> ids);
}
