package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

private const val REQUEST_CODE_GAME_LIST = 0
class GamePageFragment : Fragment() {
    private lateinit var gameClock : TextView
    private lateinit var team1Score : TextView
    private lateinit var team2Score : TextView
    private lateinit var team1Name : TextView
    private lateinit var team2Name : TextView
    private lateinit var add3Left : Button
    private lateinit var add3Right : Button
    private lateinit var add2Left : Button
    private lateinit var add2Right : Button
    private lateinit var add1Left : Button
    private lateinit var add1Right : Button
    private lateinit var reset : Button
    private lateinit var startGame : Button
    private lateinit var saveGame : Button
    private lateinit var newTeams : Button
    private lateinit var currGameModel : gameModel
    var currTimer: CountDownTimer? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_page, container, false)
        team1Name = view.findViewById(R.id.leftTeam)
        team2Name = view.findViewById(R.id.rightTeam)
        gameClock = view.findViewById(R.id.gameClock)
        team1Score = view.findViewById(R.id.scoreLeft)
        team2Score = view.findViewById(R.id.scoreRight)
        add3Left = view.findViewById(R.id.add3left)
        add3Left.setOnClickListener { addScore(add3Left) }
        add3Right = view.findViewById(R.id.add3right)
        add3Right.setOnClickListener { addScore(add3Right) }
        add2Left = view.findViewById(R.id.add2left)
        add2Left.setOnClickListener { addScore(add2Left) }
        add2Right = view.findViewById(R.id.add2right)
        add2Right.setOnClickListener { addScore(add2Right) }
        add1Right = view.findViewById(R.id.add1right)
        add1Right.setOnClickListener { addScore(add1Right) }
        add1Left = view.findViewById(R.id.add1left)
        add1Left.setOnClickListener { addScore(add1Left) }
        reset = view.findViewById(R.id.reset)
        reset.setOnClickListener { reset() }
        startGame = view.findViewById(R.id.startClock)
        startGame.setOnClickListener { startClock() }
        saveGame = view.findViewById(R.id.save)
        saveGame.setOnClickListener { save() }
        newTeams = view.findViewById(R.id.newTeams)
        newTeams.setOnClickListener { newTeams() }
        val activity : gamePage? = activity as gamePage?
        currGameModel = activity!!.getData()
        team1Name.text = currGameModel.team1Name
        team2Name.text = currGameModel.team2Name
        team1Score.text = currGameModel.team1Score
        team2Score.text = currGameModel.team2Score
        gameClock.text = currGameModel.gameClock
        return view
    }
    fun addScore(v:View?){
        when(v?.id){
            R.id.add3left -> currGameModel.team1Score = (currGameModel.team1Score.toInt() + 3).toString()
            R.id.add3right -> currGameModel.team2Score = (currGameModel.team2Score.toInt() + 3).toString()
            R.id.add2left -> currGameModel.team1Score = (currGameModel.team1Score.toInt() + 2).toString()
            R.id.add2right -> currGameModel.team2Score = (currGameModel.team2Score.toInt() + 2).toString()
            R.id.add1left -> currGameModel.team1Score = (currGameModel.team1Score.toInt() + 1).toString()
            R.id.add1right -> currGameModel.team2Score = (currGameModel.team2Score.toInt() + 1).toString()
        }
        team1Score.text = currGameModel.team1Score
        team2Score.text = currGameModel.team2Score
    }
    fun reset(){
        currTimer?.cancel()
        currTimer = null
        currGameModel.gameClock = currGameModel.gameTime + ":" + "00"
        currGameModel.team1Score = "0"
        currGameModel.team2Score = "0"
        team1Score.text = currGameModel.team1Score
        team2Score.text = currGameModel.team2Score
        gameClock.text = currGameModel.gameClock

    }
    fun startClock(){
        if(currGameModel.gameTime == getString(R.string.gameOver)){
            currGameModel.gameClock = currGameModel.gameTime + ":" + "00"
            gameClock.text = currGameModel.gameClock
        }
        else{
            val milliGameTime = (currGameModel.gameTime.toLong()) * 60 * 1000
            println(milliGameTime)
            clock(milliGameTime)
        }
    }
    fun newTeams(){
        val intent = Intent(activity, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
    fun save(){
        val intent = Intent(activity, GameList::class.java).apply{
            putExtra("currGameModel",currGameModel)
        }

        startActivityForResult(intent, REQUEST_CODE_GAME_LIST)
    }
    fun clock(x: Long) {
        currTimer?.cancel()
        currTimer = object : CountDownTimer(x, 1000){
            override fun onTick(tillFin: Long) {
                if(((tillFin/1000)%60) < 10){
                    currGameModel.gameClock = "" + ((tillFin/1000)/60) + ":0" + ((tillFin/1000)%60)
                    gameClock.text = currGameModel.gameClock
                }
                else{
                    currGameModel.gameClock = "" + ((tillFin/1000)/60) + ":" + ((tillFin/1000)%60)
                    gameClock.text = currGameModel.gameClock
                }

            }

            override fun onFinish() {
                currGameModel.gameClock = getString(R.string.gameOver)
                gameClock.text = currGameModel.gameClock
            }
        }.start()
    }
}