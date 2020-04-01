package com.cjl.emall.usermanage.service.impl;

import com.cjl.emall.bean.UserInfo;
import com.cjl.emall.service.UserService;
import com.cjl.emall.usermanage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> findAll() {
        return userMapper.selectAll();
    }
}
