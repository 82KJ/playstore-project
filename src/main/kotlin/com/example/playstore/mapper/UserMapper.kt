package com.example.playstore.mapper

import com.example.playstore.model.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UserMapper {

    @Insert("INSERT INTO user(id, password) VALUES(#{user.id}, #{user.password})")
    fun insert(@Param("user") user:User)

}