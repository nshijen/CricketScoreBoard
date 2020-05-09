package com.shijen.cricketscoreboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shijen.cricketscoreboard.Entities.Match
import com.shijen.cricketscoreboard.Entities.ScoreResult
import kotlinx.android.synthetic.main.fragment_scoreboard.*
import kotlinx.android.synthetic.main.fragment_scoreboard.view.*
import java.lang.StringBuilder

class FragmentScoreboard : Fragment(),View.OnClickListener {
    var match:Match = Match()
    val overUpdateText:StringBuilder = StringBuilder()
    var adapter = PlayersAdapter()
    var gameover = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_scoreboard, container, false)
        initViews(root)
        return root
    }

    private fun initViews(root:View) {
        root.btn_bowl.setOnClickListener(this)
        root.rv_score_board.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        root.rv_score_board.adapter = adapter
        adapter.updatePlayers(match.getClonedList())
    }

    fun bowlerBowl(){
        if(!gameover){
            if(match.bowler.numberOfBalls == 6){
                match.swapRunner()
                match.bowler.numberOfBalls = 0
                overUpdateText.clear();
                if(match.updateOvers() == 4){
                    tv_over_update.setText("GAME OVER")
                    gameover = true
                    return
                }
            }
            val scoreResult = match.bowler.bowl()
            when(scoreResult){
                ScoreResult.SINGLE,
                ScoreResult.TRIPLE-> {
                    match.updateScores(scoreResult.runs)
                    match.swapRunner()
                }
                ScoreResult.DOUBLE,
                ScoreResult.FOUR,
                ScoreResult.SIX->{
                    match.updateScores(scoreResult.runs)
                }
                ScoreResult.OUT->match.newPlayer()
                ScoreResult.WIDE->match.updateExtras(scoreResult.runs)
            }
            updateOver(scoreResult)
            updateScoresBoard()
            adapter.updatePlayers(match.getClonedList())
        }
    }
    private fun updateScoresBoard(){
        tv_total_score.setText("Total Score: "+match.totalScore)
        tv_overs.setText("OVERS:"+match.totalOvers+"."+match.bowler.numberOfBalls)
    }
    private fun updateOver(scoreResult: ScoreResult) {
        overUpdateText.appendln(scoreResult.resultString)
        tv_over_update.setText(overUpdateText)
    }

    override fun onClick(v: View?) {
        bowlerBowl()
    }
}
