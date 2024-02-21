package com.kjq.mapper;


import com.kjq.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User queryNameUser(@Param("username") String username);
    User selectIdUser(Integer id);
}
