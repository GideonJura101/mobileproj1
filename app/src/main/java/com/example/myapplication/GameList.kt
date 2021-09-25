package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "GameList"
class GameList : AppCompatActivity(){
    private lateinit var winner : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContentView(R.layout.activity_game_list)
        winner = intent.extras?.get("winner") as String
        val currFragment = supportFragmentManager.findFragmentById(R.id.fragment_container2)
        if(currFragment == null){
            val fragment = GameListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container2, fragment).commit()
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
    fun getWinner(): String{
        return winner
    }
    fun back(v : View){
        val data = Intent().apply {
            putExtra("Test", "test")
        }
        setResult(Activity.RESULT_OK, data)

    }
}