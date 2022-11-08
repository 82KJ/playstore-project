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

    @Select("SELECT * FROM game")
    fun findAllGame(): List<Game>

    @Select("SELECT * FROM game WHERE name LIKE '%'+searchName+'%'")
    fun findGame(searchName:String): List<Game>

    @Select("SELECT * FROM game WHERE id=#{gameId}")
    fun findGameWithId(@Param("gameId") gameId:Int): Game

    @Insert("INSERT INTO game(name, description, price, img_name, img_path, limit_age) VALUES(#{game.name}, #{game.description}, #{game.price}, #{game.img_name}, #{game.img_path}, #{game.limit_age})")
    fun saveGame(@Param("game") game: Game)

    @Delete("DELETE FROM game WHERE id=#{gameId}")
    fun deleteGame(@Param("gameId") gameId:Int): Boolean

    @Update("UPDATE game SET name=#{game.name}, description=#{game.description}, price=#{game.price}, limit_age=#{game.limit_age} WHERE id=#{gameId}")
    fun modifyGame(@Param("game") game:Game, @Param("gameId") gameId:Int) : Boolean

    @Update("UPDATE game SET name=#{game.name}, description=#{game.description}, price=#{game.price}, img_path=#{game.img_path}, img_name=#{game.img_name}, limit_age=#{game.limit_age} WHERE id=#{gameId}")
    fun modifyGameWithImg(@Param("game") game:Game, @Param("gameId") gameId:Int) : Boolean

}