package com.example.playstore.mapper

import com.example.playstore.model.Account
import com.example.playstore.model.AccountCoreInfo
import org.apache.ibatis.annotations.Delete
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
    fun findAccount(@Param("id") id:String):AccountCoreInfo?

    @Select("INSERT INTO basket(account_id, game_id) VALUES(#{account_id}, #{game_id})")
    fun saveBasket(@Param("account_id") account_id:String, @Param("game_id") game_id:Int)

    @Select("SELECT game_id FROM basket WHERE account_id=#{account_id}")
    fun findGameInBasket(@Param("account_id") account_id:String): MutableList<Int>?

    @Delete("DELETE FROM basket WHERE account_id=#{accountId} and game_id=#{gameId}")
    fun deleteGameInBasket(@Param("accountId") accountId: String, @Param("gameId") gameId: Int)

    @Select("SELECT game_id, playtime FROM account_and_game WHERE account_id=#{accountId}")
    fun findMyGame(@Param("accountId") accountId: String): MutableList<Pair<Int, LocalDateTime?>>?

    @Insert("INSERT INTO account_and_game(account_id, game_id) VALUES(#{accountId}, #{gameId})")
    fun saveGameList(@Param("accountId") accountId:String, @Param("gameId") gameId:Int)

}