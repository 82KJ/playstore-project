package com.example.playstore.mapper

import com.example.playstore.model.Account
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface AccountMapper {

    @Insert("INSERT INTO account(id, password, is_admin) VALUES(#{account.id}, #{account.password}, #{account.is_admin})")
    fun insert(@Param("account") account: Account)

    @Select("SELECT * FROM account WHERE id=#{id} AND password=#{password}")
    fun findAccount(@Param("id") id:String, @Param("password") password:String):Account?

}