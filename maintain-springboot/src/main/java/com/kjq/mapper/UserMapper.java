package com.kjq.mapper;


import com.kjq.POJO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    User queryAccountUser(@Param("account") String account);
    User selectIdUser(Integer id);

    @Insert("insert into `user` " +
            "(username, user_password, user_account, user_status, create_time, user_role) " +
            "values (#{username}, #{userPassword}, #{userAccount}, #{userStatus}, #{createTime}, #{userRole})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean addUser(User addUser);

    @Update("update `user` set avatar_url = #{avatarUrl} where id = #{id} and user_status = 1")
    boolean updateAvatarUrl(User user);

    @Update("update `user` set username = #{username}, gender = #{gender} where id = #{id} and user_status = 1")
    boolean updateUser(User user);

    @Select("select * from `user` where user_status = 1 limit #{page}, #{limit}")
    List<User> getAdminUsers(Integer page, Integer limit);

    @Update("update `user` set username = #{username}, user_password = #{password} where id = #{id} and user_status = 1")
    boolean updateMaintainAdmin(String username, String password, Integer id);

    @Select("select count(*) from `user` where user_status = 1")
    Integer getTotal();

    @Update("update `user` set username = #{username}, user_account = #{userAccount}, user_password = #{userPassword}, gender = #{gender}, user_role = #{userRole} where id = #{id}")
    boolean updateAdminUser(User user);

    @Update("update `user` set user_status = 0 where id = #{id}")
    boolean deleteAdminUser(Integer id);
}
