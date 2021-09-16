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
    var gameTime = ""
    var currTimer: CountDownTimer? = null
    private val gameViewModel : scoreViewModel by lazy{
        ViewModelProvider(this).get(scoreViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContentView(R.layout.activity_game_page)
        val gameClock = findViewById<TextView>(R.id.gameClock)
        val team1 = findViewById<TextView>(R.id.leftTeam)
        val team2 = findViewById<TextView>(R.id.rightTeam)
        val team1Score = findViewById<TextView>(R.id.scoreLeft)
        val team2Score = findViewById<TextView>(R.id.scoreRight)
        val bundle = intent.extras
        gameViewModel.team1Name = bundle?.getString("team1Name")!!
        gameViewModel.team2Name = bundle.getString("team2Name")!!
        gameViewModel.gameTime = bundle.getString("gameTime")!!
        if(gameViewModel.gameClock =="") {
            gameViewModel.gameClock = bundle.getString("gameTime")!! + ":" + "00"
        }
        team1.text = gameViewModel.team1Name
        team2.text = gameViewModel.team2Name
        team1Score.text = gameViewModel.team1Score
        team2Score.text = gameViewModel.team2Score
        gameClock.text = gameViewModel.gameClock
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
        if(gameMins[0] != gameViewModel.gameTime){
            clock((gameMins[0].toLong() * 60 * 1000) + (gameMins[1].toLong()*1000))
        }
    }
    fun addScore(v:View?){
        val team1Score = findViewById<TextView>(R.id.scoreLeft)
        val team2Score = findViewById<TextView>(R.id.scoreRight)
        when(v?.id){
            R.id.add3left -> gameViewModel.team1Score = (gameViewModel.team1Score.toInt() + 3).toString()
            R.id.add3right -> gameViewModel.team2Score = (gameViewModel.team2Score.toInt() + 3).toString()
            R.id.add2left -> gameViewModel.team1Score = (gameViewModel.team1Score.toInt() + 2).toString()
            R.id.add2right -> gameViewModel.team2Score = (gameViewModel.team2Score.toInt() + 2).toString()
            R.id.add1left -> gameViewModel.team1Score = (gameViewModel.team1Score.toInt() + 1).toString()
            R.id.add1right -> gameViewModel.team2Score = (gameViewModel.team2Score.toInt() + 1).toString()
        }
        team1Score.text = gameViewModel.team1Score
        team2Score.text = gameViewModel.team2Score
    }
    fun reset(v:View){
        val team1Score = findViewById<TextView>(R.id.scoreLeft)
        val team2Score = findViewById<TextView>(R.id.scoreRight)
        val gameClock = findViewById<TextView>(R.id.gameClock)
        currTimer?.cancel()
        currTimer = null
        gameViewModel.gameClock = gameViewModel.gameTime + ":" + "00"
        gameViewModel.team1Score = "0"
        gameViewModel.team2Score = "0"
        team1Score.text = gameViewModel.team1Score
        team2Score.text = gameViewModel.team2Score
        gameClock.text = gameViewModel.gameClock

    }
    fun startClock(v:View){
        val gameClock = findViewById<TextView>(R.id.gameClock)
        if(gameViewModel.gameTime == getString(R.string.gameOver)){
            gameViewModel.gameClock = gameViewModel.gameTime + ":" + "00"
            gameClock.text = gameViewModel.gameClock
        }
        else{
            val milliGameTime = (gameViewModel.gameTime.toLong()) * 60 * 1000
            println(milliGameTime)
            clock(milliGameTime)
        }
    }
    fun newTeams(v: View){
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
    fun save(v: View){
        val intent = Intent(this, GameList::class.java).apply{
            putExtra("gameViewModel",gameViewModel)
        }

        startActivityForResult(intent, REQUEST_CODE_GAME_LIST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK){
            return
        }
        if(requestCode == REQUEST_CODE_GAME_LIST){
            Log.d(TAG, "Result Obtained")
        }
    }
    fun clock(x: Long) {
        val gameClock = findViewById<TextView>(R.id.gameClock)
        currTimer?.cancel()
        currTimer = object : CountDownTimer(x, 1000){
            override fun onTick(tillFin: Long) {
                if(((tillFin/1000)%60) < 10){
                    gameViewModel.gameClock = "" + ((tillFin/1000)/60) + ":0" + ((tillFin/1000)%60)
                    gameClock.text = gameViewModel.gameClock
                }
                else{
                    gameViewModel.gameClock = "" + ((tillFin/1000)/60) + ":" + ((tillFin/1000)%60)
                    gameClock.text = gameViewModel.gameClock
                }

            }

            override fun onFinish() {
                gameViewModel.gameClock = getString(R.string.gameOver)
                gameClock.text = gameViewModel.gameClock
            }
        }.start()
    }
}