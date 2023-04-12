package com.example.itheima_reggie_take.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.itheima_reggie_take.entity.Category;

/**
 * “IService 接口简单来说就是对进 CRUD 的进一步封装,
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
