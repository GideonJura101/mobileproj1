package com.example.myapplication

import android.app.Application
import android.util.Log
import java.util.*

private const val TAG = "GameApplication"
class GameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GameRepository.initialize(this)
        val gameRepository = GameRepository.get()
        Log.d(TAG, gameRepository.checkEmpty().toString())
        if(gameRepository.checkEmpty() == 0){
            for(i in 0 until 150){
                val gameData = gameData()
                gameData.teamAName = "TeamA${i}"
                gameData.teamBName = "TeamB${i}"
                gameData.teamAScore = "$i"
                gameData.teamBScore = "" + (150-i)
                gameData.date = Date()
                gameData.gameTime = "60"
                Log.d(TAG, "inserted")
                gameRepository.insertGame(gameData)
            }
        }
    }
}