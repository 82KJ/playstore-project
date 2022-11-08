package com.example.playstore.service

import com.example.playstore.mapper.GameMapper
import com.example.playstore.model.Game
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

@Service
class GameService {

    @Autowired
    lateinit var gameMapper: GameMapper

    fun findAllGame():List<Game>{
        return gameMapper.findAllGame()
    }

    fun findGame(searchName:String):List<Game>{
        return gameMapper.findGame(searchName)
    }

    fun findGame(gameId:Int): Game{
        return gameMapper.findGameWithId(gameId)
    }

    fun deleteGame(gameId:Int): Boolean{
        return gameMapper.deleteGame(gameId)
    }

    fun addGame(game:Game, img:MultipartFile){
        var imgPath:String = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs"

        var uuid:String = UUID.randomUUID().toString()

        var imgName:String = uuid + "_" + img.originalFilename
        var file:File = File(imgPath, imgName)

        img.transferTo(file)

        game.img_name = imgName
        game.img_path = "/imgs/" + imgName

        gameMapper.saveGame(game)
    }

    fun modifyGame(game:Game, gameId: Int): Boolean {
        return gameMapper.modifyGame(game, gameId)
    }

    fun modifyGame(game: Game, img: MultipartFile, gameId:Int): Boolean {

        var imgPath:String = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs"

        var uuid:String = UUID.randomUUID().toString()

        var imgName:String = uuid + "_" + img.originalFilename
        var file:File = File(imgPath, imgName)

        img.transferTo(file)

        game.img_name = imgName
        game.img_path = "/imgs/" + imgName

        return gameMapper.modifyGameWithImg(game, gameId)
    }

}