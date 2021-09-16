package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    private lateinit var team1Name : EditText
    private lateinit var team2Name : EditText
    private lateinit var gameTime : EditText
    private lateinit var newTeamSubmit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        team1Name = view.findViewById(R.id.newTeam1)
        team2Name = view.findViewById(R.id.newTeam2)
        gameTime = view.findViewById(R.id.timeSelection)
        newTeamSubmit = view.findViewById(R.id.newTeamSubmit)
        newTeamSubmit.setOnClickListener {
            startGame()
        }
        return view
    }
    fun startGame(){
        val team1NameText = team1Name.text.toString()
        val team2NameText = team2Name.text.toString()
        val gameTimeText = gameTime.text.toString()
        val bundle = Bundle()
        bundle.putString("team1Name", team1NameText)
        bundle.putString("team2Name", team2NameText)
        bundle.putString("gameTime", gameTimeText)
        val intent = Intent(activity, gamePage::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }
}