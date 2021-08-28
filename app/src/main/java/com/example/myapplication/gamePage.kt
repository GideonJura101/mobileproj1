package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class gamePage : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        val gameClock = findViewById<TextView>(R.id.gameClock)
        val team1 = findViewById<TextView>(R.id.leftTeam)
        val team2 = findViewById<TextView>(R.id.rightTeam)

        val add3left = findViewById<Button>(R.id.add3left) as Button
        add3left.setOnClickListener(this)
        val add3right = findViewById<Button>(R.id.add3right) as Button
        add3right.setOnClickListener(this)
        val add2left = findViewById<Button>(R.id.add2left) as Button
        add2left.setOnClickListener(this)
        val add2right = findViewById<Button>(R.id.add2right) as Button
        add2right.setOnClickListener(this)
        val add1left = findViewById<Button>(R.id.add1left) as Button
        add1left.setOnClickListener(this)
        val add1right = findViewById<Button>(R.id.add1right) as Button
        add1right.setOnClickListener(this)
        val newGame = findViewById<Button>(R.id.newTeams)
        newGame.setOnClickListener(this)
        val bundle = intent.extras
        val team1Name = bundle!!.getString("team1Name")
        val team2Name = bundle!!.getString("team2Name")
        val gameTime = bundle!!.getString("gameTime")
        team1.text = team1Name
        team2.text = team2Name
        gameClock.text = gameTime
    }
    fun clock(x: Long) {
        val gameClock = findViewById<TextView>(R.id.gameClock)
        object : CountDownTimer(x, 1000){
            override fun onTick(tillFin: Long) {
                val intFin = (tillFin/1000).toInt()
                val mins = (intFin/1000)/60
                val secs = (intFin/1000) % 60
                gameClock.text = mins.toString() + ":" + secs.toString()
            }

            override fun onFinish() {
                gameClock.text = getString(R.string.gameOver)
            }
        }.start()
    }
    override fun onClick(v:View?){
        val team1Score = findViewById<TextView>(R.id.scoreLeft)
        val team2Score = findViewById<TextView>(R.id.scoreRight)
        when(v?.id){
            R.id.add3left -> team1Score.setText((team1Score.text.toString().toInt() + 3).toString())
            R.id.add3right -> team2Score.setText((team1Score.text.toString().toInt() + 3).toString())
            R.id.add2left -> team1Score.setText((team1Score.text.toString().toInt() + 2).toString())
            R.id.add2right -> team2Score.setText((team1Score.text.toString().toInt() + 2).toString())
            R.id.add1left -> team1Score.setText((team1Score.text.toString().toInt() + 1).toString())
            R.id.add1right -> team2Score.setText((team1Score.text.toString().toInt() + 1).toString())
            R.id.reset ->{
                val gameClock = findViewById<TextView>(R.id.gameClock)
                val gameTime = intent.extras!!.getString("gameTime")
                gameClock.setText(gameTime + ":" + "00")
                team1Score.setText("0")
                team2Score.setText("0")
            }
            R.id.startClock ->{
                val gameClock = findViewById<TextView>(R.id.gameClock)
                val gameTime = intent.extras!!.getString("gameTime")
                if(gameClock.text.toString().equals(getString(R.string.gameOver))){
                    gameClock.text = gameTime + ":" + "00"
                }
                else{
                    val milliGameTime = gameTime!!.toLong() * 60 * 1000
                    clock(milliGameTime)
                }
            }
            R.id.newTeams -> {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
            else -> {println("failed")}
        }
    }
}