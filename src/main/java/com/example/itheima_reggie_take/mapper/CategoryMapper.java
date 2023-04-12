package com.example.itheima_reggie_take.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.itheima_reggie_take.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 使用mybats plus 的方式 创建 mapper 然后使用 baseMapper 里面泛型添加 xxx 实体类
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
