package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.gameData
import java.util.*

@Dao
interface GameDao {

    @Query("SELECT * FROM table_game")
    fun getGames():LiveData<List<gameData>>

    @Query("SELECT * FROM table_game WHERE id = (:id)")
    fun getGame(id: UUID): LiveData<gameData?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(gameData: gameData)

    @Query("Delete FROM table_game")
    fun deleteAll()
}