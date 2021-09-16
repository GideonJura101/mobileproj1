package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private const val TAG = "gamePage"
private const val REQUEST_CODE_GAME_LIST = 0
class gamePage : AppCompatActivity(){
    private val gameViewModel : scoreViewModel by lazy{
        ViewModelProvider(this).get(scoreViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContentView(R.layout.activity_game_page)
        val bundle = intent.extras
        gameViewModel.team1Name = bundle?.getString("team1Name")!!
        gameViewModel.team2Name = bundle.getString("team2Name")!!
        gameViewModel.gameTime = bundle.getString("gameTime")!!
            gameViewModel.gameClock = bundle.getString("gameTime")!! + ":" + "00"
        val currFragment = supportFragmentManager.findFragmentById(R.id.fragment_container1)
        if(currFragment == null){
            val fragment = GamePageFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container1, fragment).commit()
        }

    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart Called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause Called")
    }
    override fun onResume(){
        super.onResume()

        Log.d(TAG, "onResume Called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop Called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }



    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val gameMins = gameViewModel.gameClock.split(":")
            /**if(gameMins[0] != gameViewModel.gameTime){
            clock((gameMins[0].toLong() * 60 * 1000) + (gameMins[1].toLong()*1000))
        }*/
    }
    fun getData(): scoreViewModel {
        return gameViewModel
    }
}