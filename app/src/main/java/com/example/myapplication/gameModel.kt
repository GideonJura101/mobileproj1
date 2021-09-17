package com.example.myapplication

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

private const val TAG = "scoreViewModel"
open class gameModel : Serializable  {
    var gameIndex = ""
    var team1Name = ""
    var team2Name = ""
    var gameTime = ""
    var gameClock = ""
    var team1Score = "0"
    var team2Score = "0"
    var winTeam = 0
    var gameDate: LocalDate = LocalDate.now()
    init{
        whoWon()
        Log.d(TAG, "scoreViewModel instance Created")
    }
    fun whoWon(){
        if(team1Score.toInt() > team2Score.toInt()){
            winTeam = 1
        }
        else if(team2Score.toInt() > team1Score.toInt()){
            winTeam = 2
        }
        else{
            winTeam = 0
        }
    }
}