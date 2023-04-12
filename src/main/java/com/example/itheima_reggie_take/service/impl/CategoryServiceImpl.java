package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.common.CustomException;
import com.example.itheima_reggie_take.entity.Category;
import com.example.itheima_reggie_take.entity.Dish;
import com.example.itheima_reggie_take.entity.Setmeal;
import com.example.itheima_reggie_take.mapper.CategoryMapper;
import com.example.itheima_reggie_take.service.CategoryService;
import com.example.itheima_reggie_take.service.DishService;
import com.example.itheima_reggie_take.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id 删除分类，删除之前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品， 如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id 进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类 是否关联了菜品，如果已经关联，抛出一个业务异常
        if (count > 0) {
            // 已关联菜品，抛出业务异常
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐， 如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id 进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);

        //查询当前分类 是否关联了套餐，如果已经关联，抛出一个业务异常
        if (count1 > 0) {
            // 已关联套餐，抛出业务异常
            throw new CustomException("当前分类下关联了套餐,不能删除");
        }
        // 正常删除分类
        super.removeById(id);
    }
}
