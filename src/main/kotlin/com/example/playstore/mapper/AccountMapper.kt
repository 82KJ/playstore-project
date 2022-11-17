package com.example.playstore.mapper

import com.example.playstore.model.Account
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import java.time.LocalDateTime

@Mapper
interface AccountMapper {

    @Insert("INSERT INTO account(id, password, is_admin, birthDate) VALUES(#{account.id}, #{account.password}, #{account.is_admin}, #{account.birthDate})")
    fun insert(@Param("account") account: Account)

    @Select("SELECT * FROM account WHERE id=#{id}")
    fun findAccount(@Param("id") id:String):Account?

    @Select("INSERT INTO basket(account_id, game_id) VALUES(#{account_id}, #{game_id})")
    fun saveBasket(@Param("account_id") account_id:String, @Param("game_id") game_id:Int)

    @Select("SELECT game_id FROM basket WHERE account_id=#{account_id}")
    fun findGameInBasket(@Param("account_id") account_id:String): MutableList<Int>?

    @Select("SELECT game_id, playtime FROM account_and_game WHERE account_id=#{account_id}")
    fun findMyGame(@Param("account_id") account_id: String): MutableList<Pair<Int, LocalDateTime?>>?
}