package com.example.itheima_reggie_take.dto;

import com.example.itheima_reggie_take.entity.Dish;
import com.example.itheima_reggie_take.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {
    // 菜品对应的口味
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
