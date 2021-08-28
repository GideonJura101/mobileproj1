package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
    }
    fun startGame(view: View){
        val team1Name = findViewById<EditText>(R.id.newTeam1)
        val team1NameText = team1Name.text.toString()
        val team2Name = findViewById<EditText>(R.id.newTeam2)
        val team2NameText = team2Name.text.toString()
        val gameTime = findViewById<EditText>(R.id.timeSelection)
        val gameTimeText = gameTime.text.toString()
        val bundle = Bundle()
        bundle.putString("team1Name", team1NameText)
        bundle.putString("team2Name", team2NameText)
        bundle.putString("gameTime", gameTimeText)
        val intent = Intent(this, gamePage::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }
}