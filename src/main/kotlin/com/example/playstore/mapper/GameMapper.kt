package com.example.playstore.mapper

import com.example.playstore.model.Game
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update


@Mapper
interface GameMapper {


    @Select("SELECT * FROM game WHERE invisible=0")
    fun findVisibleGame(): List<Game>

    @Select("SELECT * FROM game WHERE name LIKE CONCAT('%', #{gameName}, '%') and invisible = 0")
    fun findVisibleGameWithName(@Param("gameName") gameName:String):List<Game>

    @Select("SELECT * FROM game WHERE id=#{gameId}")
    fun findGameWithId(@Param("gameId") gameId:Int): Game

    @Insert("INSERT INTO game(name, description, price, main_img_name, main_img_path, limit_age, sub_img_name, sub_img_path, invisible) VALUES(#{game.name}, #{game.description}, #{game.price}, #{game.main_img_name}, #{game.main_img_path}, #{game.limit_age}, #{game.sub_img_name}, #{game.sub_img_path}, #{game.invisible})")
    fun saveGame(@Param("game") game: Game)

    @Update("UPDATE game SET invisible=1 WHERE id=#{gameId}")
    fun deleteGame(@Param("gameId") gameId:Int): Boolean

    @Update("UPDATE game SET name=#{game.name}, description=#{game.description}, price=#{game.price}, main_img_path=#{game.main_img_path}, main_img_name=#{game.main_img_name}, limit_age=#{game.limit_age}, sub_img_path=#{game.sub_img_path}, sub_img_name=#{game.sub_img_name} WHERE id=#{gameId}")
    fun modifyGame(@Param("game") game:Game, @Param("gameId") gameId:Int) : Boolean

}