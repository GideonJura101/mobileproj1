package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContentView(R.layout.activity_main)
        val currFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(currFragment == null){
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val team1Name = findViewById<EditText>(R.id.newTeam1)
        val team1NameText = team1Name.text.toString()
        val team2Name = findViewById<EditText>(R.id.newTeam2)
        val team2NameText = team2Name.text.toString()
        val gameTime = findViewById<EditText>(R.id.timeSelection)
        val gameTimeText = gameTime.text.toString()
        outState.putString("team1Name", team1NameText)
        outState.putString("team2Name", team2NameText)
        outState.putString("gameTime", gameTimeText)
        super.onSaveInstanceState(outState)
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
        val team1Name = findViewById<EditText>(R.id.newTeam1)
        team1Name.setText(savedInstanceState.getString("team1Name"))
        val team2Name = findViewById<EditText>(R.id.newTeam2)
        team2Name.setText(savedInstanceState.getString("team2Name"))
        val gameTime = findViewById<EditText>(R.id.timeSelection)
        gameTime.setText(savedInstanceState.getString("gameTime"))
    }
}