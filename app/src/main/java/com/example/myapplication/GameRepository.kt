package com.example.myapplication

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.myapplication.database.GameDatabase
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "table_game"
class GameRepository private constructor(context : Context) {
    private val database : GameDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    private val gameDao = database.gameDao()

    fun getGames(): LiveData<List<gameData>> = gameDao.getGames()

    fun getGame(id:UUID):LiveData<gameData?> = gameDao.getGame(id)

    fun insertGame(gameData: gameData) = gameDao.insertGame(gameData)
    fun deleteAll() = gameDao.deleteAll()
    fun updateGame(gameData: gameData) = gameDao.updateGame(gameData)

    companion object{
        private var INSTANCE: GameRepository? = null
        fun initialize(context : Context){
            if(INSTANCE == null){
                INSTANCE = GameRepository(context)
            }
        }
        fun get(): GameRepository{
            return INSTANCE?:
            throw IllegalStateException("GameRepository must be initialized")
        }
    }

}