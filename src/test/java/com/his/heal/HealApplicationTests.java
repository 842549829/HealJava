package com.his.heal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.his.heal.entity.User;
import com.his.heal.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        var d = userMapper.findAll();
        System.out.println(d.getFirst().getId());
    }

    @Test
    void selectById() {
        var d = userMapper.selectById("3a194f8d-d797-004e-d3b2-89c6a9684089");
        System.out.println(d.getUserName());
    }


    @Test
    void testQuery(){
        var lwq = new LambdaQueryWrapper<User>();
        lwq.eq(User::getUserName,"<UNK>");
        lwq.or(wep-> wep.ne(User::getId,"") );
        var d = userMapper.selectOne(lwq);
    }

}
