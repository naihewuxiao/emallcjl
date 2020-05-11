package com.cjl.emall.passport;

import com.cjl.emall.passport.util.JwtUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class EmallPassportWebApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test01(){
        String key = "atguigu";
        String ip="192.168.67.201";
        Map map = new HashMap();
        map.put("userId","1001");
        map.put("nickName","marry");
        String token = JwtUtil.encode(key, map, ip);
        Map<String, Object> decode = JwtUtil.decode(token, key, "192.168.67.102");
    }


}
