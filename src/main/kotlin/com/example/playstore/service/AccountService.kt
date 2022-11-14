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
        return accountMapper.findAccount(id)
    }

    fun saveBasket(account_id:String, gameId:Int): MutableList<Int>? {
        accountMapper.saveBasket(account_id, gameId)
        var gameInBasket = accountMapper.findGameInBasket(account_id)
        var games : MutableList<Int>? = mutableListOf()

        gameInBasket?.forEach{
            games?.add(it.third)
        }

        return games
    }
}