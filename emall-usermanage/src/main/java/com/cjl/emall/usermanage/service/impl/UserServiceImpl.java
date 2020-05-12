package com.cjl.emall.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.cjl.emall.bean.UserAddress;
import com.cjl.emall.bean.UserInfo;
import com.cjl.emall.config.RedisUtil;
import com.cjl.emall.service.UserService;
import com.cjl.emall.usermanage.mapper.UserAddressMapper;
import com.cjl.emall.usermanage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service(timeout = 600000)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private RedisUtil redisUtil;

    private String userKey_prefix="user:";
    private String userinfoKey_suffix=":info";
    private int userKey_timeOut=60*60*24;


    @Override
    public List<UserInfo> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> res = userAddressMapper.select(userAddress);
        return res;
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        String password = DigestUtils.md5DigestAsHex(userInfo.getPasswd().getBytes());
        userInfo.setPasswd(password);
        UserInfo info = userMapper.selectOne(userInfo);

        if (info!=null){
            // 获得到redis ,将用户存储到redis中
            Jedis jedis = redisUtil.getJedis();
            jedis.setex(userKey_prefix+info.getId()+userinfoKey_suffix,userKey_timeOut, JSON.toJSONString(info));
            jedis.close();
            return  info;
        }
        return null;
    }

    @Override
    public UserInfo verify(String userId) {
        // 取得redis
        Jedis jedis = redisUtil.getJedis();
        // 拼接key
        String userKey = userKey_prefix+userId+userinfoKey_suffix;
        // 取得redis中的数据
        String userJson = jedis.get(userKey);
        // 用户处于活跃状态
        jedis.expire(userKey,userKey_timeOut);
        // 判断userJson 是否为空
        if (userJson!=null && userJson.length()>0){
            // userJson 将其转化为对象
            UserInfo userInfo = JSON.parseObject(userJson, UserInfo.class);
            return userInfo;
        }
        return null;
    }
}
