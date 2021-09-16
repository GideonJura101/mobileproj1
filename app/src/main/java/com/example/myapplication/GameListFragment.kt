package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "GameListFragment"
class GameListFragment : Fragment() {
    private val gameListViewModel : gameViewModel by lazy{
        ViewModelProvider(this).get(gameViewModel::class.java)
    }
    private lateinit var gameRecyclerView : RecyclerView
    private var adapter : GameAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total games: ${gameListViewModel.games.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gameRecyclerView = view.findViewById(R.id.gameRecyclerView) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }
    private fun updateUI(){
        val games = gameListViewModel.games
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }
    private inner class GameHolder(view:View) : RecyclerView.ViewHolder(view){
        val titleTextView : TextView = itemView.findViewById(R.id.gameTitle)
        val dateTextView : TextView = itemView.findViewById(R.id.gameDate)
        val gameTimeTextView : TextView = itemView.findViewById(R.id.gameTime)
        val team1NameTextView : TextView = itemView.findViewById(R.id.gameListTeam1)
        val team2NameTextView : TextView = itemView.findViewById(R.id.gameListTeam2)
        val team2ScoreTextView : TextView = itemView.findViewById(R.id.gameListTeam2Score)
        val team1ScoreTextView : TextView = itemView.findViewById(R.id.gameListTeam1Score)
    }
    private inner class GameAdapter(var games : List<gameModel>) : RecyclerView.Adapter<GameHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.apply {
                titleTextView.text = game.gameIndex
                dateTextView.text = game.gameDate.toString()
                gameTimeTextView.text = game.gameTime
                team1NameTextView.text = game.team1Name
                team2NameTextView.text = game.team2Name
                team1ScoreTextView.text = game.team1Score
                team2ScoreTextView.text = game.team2Score
            }
        }
    }
    companion object{
        fun newInstance() : GameListFragment{
            return GameListFragment()
        }
    }
}