package com.example.querymapper.mapper;

import com.example.querymapper.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectUserById(@Param("id") Long id);
}
