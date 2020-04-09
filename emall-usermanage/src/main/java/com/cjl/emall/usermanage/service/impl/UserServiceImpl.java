package com.cjl.emall.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cjl.emall.bean.UserAddress;
import com.cjl.emall.bean.UserInfo;
import com.cjl.emall.service.UserService;
import com.cjl.emall.usermanage.mapper.UserAddressMapper;
import com.cjl.emall.usermanage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 600000)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserInfo> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserAddress> getUserAddressList(Long userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> res = userAddressMapper.select(userAddress);
        return res;
    }
}
