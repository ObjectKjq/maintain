package com.kjq.mapper;


import com.kjq.POJO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    User queryAccountUser(@Param("account") String account);
    User selectIdUser(Integer id);

    @Insert("insert into `user` " +
            "(username, user_password, user_account, avatar_url, user_status, create_time, user_role) " +
            "values (#{username}, #{userPassword}, #{userAccount}, #{avatarUrl}, #{userStatus}, #{createTime}, #{userRole})")
    boolean addUser(User addUser);

    @Update("update `user` set avatar_url = #{avatarUrl} where id = #{id} and user_status = 1")
    boolean updateAvatarUrl(User user);

    @Update("update `user` set username = #{username}, gender = #{gender} where id = #{id} and user_status = 1")
    boolean updateUser(User user);
}
