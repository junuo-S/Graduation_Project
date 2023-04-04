package com.junuo.mapper;

import com.junuo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);

    List<User> selectAll();

    int deleteById(@Param("id") int id);

    int insertUser(User user);
}
