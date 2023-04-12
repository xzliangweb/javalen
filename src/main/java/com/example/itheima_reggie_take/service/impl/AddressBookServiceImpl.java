package com.example.itheima_reggie_take.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.itheima_reggie_take.entity.AddressBook;
import com.example.itheima_reggie_take.mapper.AddressBookMapper;
import com.example.itheima_reggie_take.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
