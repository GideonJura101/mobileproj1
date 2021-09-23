package com.example.myapplication

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

private const val TAG = "scoreViewModel"

open class gameModel : Serializable  {
    var gameIndex : String = ""
    var team1Name : String = ""
    var team2Name : String = ""
    var gameTime : String = ""
    var gameClock : String = ""
    var team1Score : String = "0"
    var team2Score : String = "0"
    var winTeam : Int = 0
    var gameDate: Date = Date()
    var id : UUID = UUID.randomUUID()
    init{
        whoWon()
        id = UUID.randomUUID()
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