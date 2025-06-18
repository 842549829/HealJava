package com.his.heal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.heal.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
    User findById(String id);
}
