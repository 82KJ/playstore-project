package com.example.playstore.service

import com.example.playstore.mapper.GameMapper
import com.example.playstore.model.Game
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameService {

    @Autowired
    lateinit var gameMapper: GameMapper

    fun findAllGame():List<Game>{
        return gameMapper.findAllGame()
    }

}