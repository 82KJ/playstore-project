package com.example.playstore.mapper

import com.example.playstore.model.Game
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select


@Mapper
interface GameMapper {

    @Select("SELECT * FROM game")
    fun findAllGame(): List<Game>

}