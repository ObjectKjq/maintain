package com.kjq.mapper;


import com.kjq.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User queryAccountUser(@Param("account") String account);
    User selectIdUser(Integer id);
}
