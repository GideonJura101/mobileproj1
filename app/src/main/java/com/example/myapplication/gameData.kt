package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_game")
data class gameData(
    @PrimaryKey var id : UUID = UUID.randomUUID(),
    var teamAName : String = "",
    var teamBName : String = "",
    var teamAScore : String = "",
    var teamBScore : String = "",
    var date : Date = Date(),
    var gameTime : String = ""
)
