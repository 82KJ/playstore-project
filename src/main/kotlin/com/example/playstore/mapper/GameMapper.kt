package com.example.playstore.mapper

import com.example.playstore.model.Game
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select


@Mapper
interface GameMapper {

    @Select("SELECT * FROM game")
    fun findAllGame(): List<Game>

    @Insert("INSERT INTO game(name, description, price, img_name, img_path, limit_age) VALUES(#{game.name}, #{game.description}, #{game.price}, #{game.img_name}, #{game.img_path}, #{game.limit_age})")
    fun saveGame(@Param("game") game: Game)

}