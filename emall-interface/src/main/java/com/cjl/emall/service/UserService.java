package com.cjl.emall.service;

import com.cjl.emall.bean.UserAddress;
import com.cjl.emall.bean.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> findAll();
    List<UserAddress> getUserAddressList(Long userId);
    UserInfo login(UserInfo userInfo);
    UserInfo verify(String userId);
}
