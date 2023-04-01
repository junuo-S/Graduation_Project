package com.junuo.mapper;

import com.junuo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
