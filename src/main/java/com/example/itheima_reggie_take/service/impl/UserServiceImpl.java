package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.entity.User;
import com.example.itheima_reggie_take.mapper.UserMapper;
import com.example.itheima_reggie_take.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
