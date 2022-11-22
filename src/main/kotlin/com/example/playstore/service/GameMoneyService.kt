럍package com.example.playstore.service

import com.example.playstore.mapper.AccountMapper
import com.example.playstore.model.AccountCoreInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameMoneyService {

    @Autowired
    lateinit var accountMapper: AccountMapper

    fun modifyGameMoney(id:String, totalGameMoney:Int): Boolean  {
        return accountMapper.modifyGameMoney(id, totalGameMoney)
    }
}