package com.example.myapplication

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import java.io.Serializable

private const val TAG = "scoreViewModel"
class scoreViewModel : Serializable, ViewModel()  {
    var team1Name = ""
    var team2Name = ""
    var gameTime = ""
    var gameClock = ""
    var team1Score = "0"
    var team2Score = "0"
    init{
        Log.d(TAG, "scoreViewModel instance Created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared Called")
    }
}