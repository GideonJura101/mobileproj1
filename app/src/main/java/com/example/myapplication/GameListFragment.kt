package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "GameListFragment"
class GameListFragment : Fragment() {
    private val gameListViewModel : gameViewModel by lazy{
        ViewModelProvider(this).get(gameViewModel::class.java)
    }
    private lateinit var gameRecyclerView : RecyclerView
    private var adapter : GameAdapter? = GameAdapter(emptyList())
    private lateinit var winner : String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        gameRecyclerView = view.findViewById(R.id.gameRecyclerView) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)
        gameRecyclerView.adapter = adapter
        val activity : GameList? = activity as GameList?
        winner = activity!!.getWinner()
        if(activity.getWinner() == "1"){
            Log.d(TAG, "Winner1")
            gameListViewModel.teamAData()
        }
        else if(activity.getWinner() == "2"){
            Log.d(TAG, "Winner2")
            gameListViewModel.teamBData()
        }
        else{
            Log.d(TAG, "Winner0")
            gameListViewModel.allData()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity : GameList = activity as GameList
        if(activity.getWinner() == "1"){
            Log.d(TAG, "Winner1")
            gameListViewModel.teamAData()
        }
        else if(activity.getWinner() == "2"){
            Log.d(TAG, "Winner2")
            gameListViewModel.teamBData()
        }
        else{
            Log.d(TAG, "Winner0")
            gameListViewModel.allData()
        }
        gameListViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            Observer {
                games -> games?.let{
                    Log.i(TAG, "Got Games ${games.size}")
                    updateUI(games)
                }
            }
        )
    }
    private fun updateUI(games : List<gameData>){

        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }
    private inner class GameHolder(view:View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var game: gameData
        val dateTextView : TextView = itemView.findViewById(R.id.gameDate)
        val gameTimeTextView : TextView = itemView.findViewById(R.id.gameTime)
        val team1NameTextView : TextView = itemView.findViewById(R.id.gameListTeam1)
        val team2NameTextView : TextView = itemView.findViewById(R.id.gameListTeam2)
        val team2ScoreTextView : TextView = itemView.findViewById(R.id.gameListTeam2Score)
        val team1ScoreTextView : TextView = itemView.findViewById(R.id.gameListTeam1Score)
        val winnerImage : ImageView = itemView.findViewById(R.id.imageView2)

        fun bind(game: gameData){
            this.game = game
            dateTextView.text = "Date: " + this.game.date.toString()
            gameTimeTextView.text = "Game Time: " + this.game.gameTime
            team1NameTextView.text = this.game.teamAName
            team2NameTextView.text = this.game.teamBName
            team1ScoreTextView.text = "Score: " + this.game.teamAScore
            team2ScoreTextView.text = "Score: " + this.game.teamBScore
            if(this.game.teamAScore.toInt() > this.game.teamBScore.toInt()){
                winnerImage.setImageResource(R.drawable.ic_solved)
            }
            else if(this.game.teamAScore.toInt() > this.game.teamBScore.toInt()){
                winnerImage.setImageResource(R.drawable._32_1328230_flame_free_icon_fire_icon)
            }
            else{
                winnerImage.setImageResource(R.drawable._32_1328230_flame_free_icon_fire_icon)
            }
        }
        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v : View){
            Toast.makeText(context, "${game.id} pressed!", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putString("team1Name", game.teamAName)
            bundle.putString("team2Name", game.teamBName)
            bundle.putString("gameTime", game.gameTime)
            bundle.putString("id", game.id.toString())
            bundle.putString("team1Score", game.teamAScore)
            bundle.putString("team2Score", game.teamBScore)
            val intent = Intent(activity, gamePage::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)

        }
    }
    private inner class GameAdapter(var games : List<gameData>) : RecyclerView.Adapter<GameHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.bind(game)
        }
    }
    companion object{
        fun newInstance() : GameListFragment{
            return GameListFragment()
        }
    }
}