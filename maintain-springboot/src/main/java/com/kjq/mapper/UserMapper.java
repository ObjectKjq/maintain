package com.kjq.mapper;


import com.kjq.POJO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User queryAccountUser(@Param("account") String account);
    User selectIdUser(Integer id);

    @Insert("insert into `user` " +
            "(username, user_account, avatar_url, user_status, create_time, user_role) " +
            "values (#{username}, #{userAccount}, #{avatarUrl}, #{userStatus}, #{createTime}, #{userRole})")
    boolean addUser(User addUser);
}
