package com.example.itheima_reggie_take.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.itheima_reggie_take.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper  extends BaseMapper<Employee> {
}
