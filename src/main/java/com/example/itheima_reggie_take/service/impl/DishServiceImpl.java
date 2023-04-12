package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.dto.DishDto;
import com.example.itheima_reggie_take.entity.Dish;
import com.example.itheima_reggie_take.entity.DishFlavor;
import com.example.itheima_reggie_take.mapper.DishMapper;
import com.example.itheima_reggie_take.service.DishFlavorService;
import com.example.itheima_reggie_take.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品同时，保存对应的口味数据
     *
     * @param dishDto
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        log.info("dishDto", dishDto);
        // 保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId(); // 菜品id

        // 菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        // 保存菜品口味数据到 菜品口味表 dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id 查询菜品信息和对应的口味信息
     *
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        // 查询菜品的基本信息
        Dish byId = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(byId, dishDto);

        // 查询菜品的口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(DishFlavor::getDishId, byId.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper1);

        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish 表基本信息
        this.updateById(dishDto);
        //清理当前菜品对应口味数据 ----dish_flavor 表的delete 操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //添加当前提交过来的口味数据 ---dish_flavor 表的insert 操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 菜品管理删除单条
     * @param ids
     */
    @Override
    public void remove(Long ids) {
        super.removeById(ids);
    }
}
