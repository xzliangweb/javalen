package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.entity.Employee;
import com.example.itheima_reggie_take.mapper.EmployeeMapper;
import com.example.itheima_reggie_take.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
