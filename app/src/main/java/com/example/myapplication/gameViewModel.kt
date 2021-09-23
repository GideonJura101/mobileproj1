package com.example.myapplication

import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class gameViewModel : ViewModel() {
    /**val games = mutableListOf<gameModel>()
    init {
        for(i in 0 until 100){
            val gameModel = gameModel()
            gameModel.gameIndex = "Game #$i"
            gameModel.team1Name = "team1#$i"
            gameModel.team2Name = "team2#$i"
            gameModel.team1Score = (i*6 % 50).toString()
            gameModel.team2Score = (i*3 % 50).toString()
            gameModel.gameDate = Date()
            gameModel.gameTime = "60"
            gameModel.whoWon()
            games += gameModel
        }
    }**/
    private val gameRepository = GameRepository.get()
    val gameListLiveData = gameRepository.getGames()
    fun addGames(){
        for(i in 0 until 150){
            val gameData = gameData()
            gameData.teamAName = "TeamA${i}"
            gameData.teamBName = "TeamB${i}"
            gameData.teamAScore = "${i}"
            gameData.teamBScore = "" + (150-i)
            gameData.date = Date()
            gameRepository.insertGame(gameData)
        }
    }
}