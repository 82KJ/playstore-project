package com.example.playstore.service

import com.example.playstore.mapper.AccountMapper
import com.example.playstore.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
        var account = accountMapper.findAccount(id)
        account?.basket = gamesInBasket
        account?.myGame = myGames

        return account
    }

    fun saveBasket(account_id:String, gameId:Int): MutableList<Int>? {
        accountMapper.saveBasket(account_id, gameId)
        var games = accountMapper.findGameInBasket(account_id)

        return games
    }
}