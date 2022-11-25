package com.example.playstore.service

import com.example.playstore.mapper.AccountMapper
import com.example.playstore.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccountService {

    @Autowired
    lateinit var accountMapper: AccountMapper

    fun saveAccount(account:Account){
        accountMapper.insert(account)
    }

    fun findAccount(id:String):Account?{
        var gamesInBasket = accountMapper.findGameInBasket(id)
        var myGames = accountMapper.findMyGame(id)
        var coreInfo = accountMapper.findAccount(id)
        var account:Account?

        if (coreInfo != null){
            account = Account(id=coreInfo.id, password=coreInfo.password, is_admin=coreInfo.is_admin, gameMoney=coreInfo.gameMoney, birthDate=coreInfo.birthDate)
            account.basket = gamesInBasket
            account.myGame = myGames

            return account
        }
        else{
            return null
        }

    }

    fun resetPassword(id:String, pw:String){
        accountMapper.resetPassword(id, pw)
    }

    fun saveBasket(accountId:String, gameId:Int): MutableList<Int>? {
        accountMapper.saveBasket(accountId, gameId)
        var games = accountMapper.findGameInBasket(accountId)

        return games
    }

    fun deleteGameInBasket(accountId: String, gameIdList:List<String>):MutableList<Int>? {
        gameIdList.forEach {
            accountMapper.deleteGameInBasket(accountId, it.toInt())
        }
        var games = accountMapper.findGameInBasket(accountId)

        return games
    }

    fun deleteGameInBasket(accountId: String, gameId:Int):MutableList<Int>? {

        accountMapper.deleteGameInBasket(accountId, gameId)
        var games = accountMapper.findGameInBasket(accountId)

        return games
    }

    fun saveGameList(accountId:String, gameIdList:List<String>): MutableList<Pair<Int, LocalDateTime?>>?{
        gameIdList.forEach {
            accountMapper.saveGameList(accountId, it.toInt())
        }

        var myGamelist = accountMapper.findMyGame(accountId)

        return myGamelist
    }

    fun getPlayTime(accountId: String, gameId: Int) {
        return accountMapper.getPlayTime(accountId, gameId)
    }

    fun setPlayTime(accountId:String, gameId:Int, playTime:LocalDateTime){
        accountMapper.setPlayTime(accountId, gameId, playTime)
    }


}