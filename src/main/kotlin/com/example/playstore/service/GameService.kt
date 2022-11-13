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

    fun findVisibleGame():List<Game>{
        return gameMapper.findVisibleGame()
    }

    fun findVisibleGame(gameName : String):List<Game>{
        return gameMapper.findVisibleGameWithName(gameName)
    }

    fun findGame(gameId:Int): Game{
        return gameMapper.findGameWithId(gameId)
    }

    fun deleteGame(gameId:Int): Boolean{
        return gameMapper.deleteGame(gameId)
    }

    fun addGame(game:Game, main_img:MultipartFile, sub_img:MultipartFile){
        var imgPath:String = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs"

        var uuid1:String = UUID.randomUUID().toString()
        var mainImgName:String = uuid1 + "_" + main_img.originalFilename
        var file1:File = File(imgPath, mainImgName)
        main_img.transferTo(file1)

        var uuid2:String = UUID.randomUUID().toString()
        var subImgName:String = uuid2 + "_" + sub_img.originalFilename
        var file2:File = File(imgPath, subImgName)
        sub_img.transferTo(file2)

        game.main_img_name = mainImgName
        game.main_img_path = "/imgs/" + mainImgName

        game.sub_img_name = subImgName
        game.sub_img_path = "/imgs/" + subImgName
        game.invisible = 0

        gameMapper.saveGame(game)
    }


    fun modifyGame(game: Game, main_img: MultipartFile, sub_img: MultipartFile, gameId:Int): Boolean {

        var cur_game = gameMapper.findGameWithId(gameId)
        game.main_img_name = cur_game.main_img_name
        game.main_img_path = cur_game.main_img_path
        game.sub_img_name = cur_game.sub_img_name
        game.sub_img_path = cur_game.sub_img_path

        var imgPath:String = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs"

        if (!main_img.isEmpty){
            var uuid1:String = UUID.randomUUID().toString()
            var mainImgName:String = uuid1 + "_" + main_img.originalFilename
            var file1:File = File(imgPath, mainImgName)
            main_img.transferTo(file1)

            game.main_img_name = mainImgName
            game.main_img_path = "/imgs/" + mainImgName
        }

        if (!sub_img.isEmpty){
            var uuid2:String = UUID.randomUUID().toString()
            var subImgName:String = uuid2 + "_" + sub_img.originalFilename
            var file2:File = File(imgPath, subImgName)
            sub_img.transferTo(file2)

            game.sub_img_name = subImgName
            game.sub_img_path = "/imgs/" + subImgName
        }

        return gameMapper.modifyGame(game, gameId)
    }

}