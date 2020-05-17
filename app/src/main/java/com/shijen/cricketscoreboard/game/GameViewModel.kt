package com.shijen.cricketscoreboard.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shijen.cricketscoreboard.entities.Match
import com.shijen.cricketscoreboard.entities.Players
import com.shijen.cricketscoreboard.entities.BallOutput
import com.shijen.cricketscoreboard.entities.BatsmanStatus

class GameViewModel : ViewModel() {

    var match: Match = Match()
    var totalUpdate: MutableLiveData<Pair<String, String>> = MutableLiveData()
    val playersScoreList: MutableLiveData<List<Players>> = MutableLiveData()
    val ballOutput: MutableLiveData<BallOutput> = MutableLiveData()
    val overUp: MutableLiveData<Boolean> = MutableLiveData()

    var gameover = false;

    fun bowl() {
        if (!gameover) {
            if (match.bowler.numberOfBalls == 6) {
                match.swapRunner()
                overUp.postValue(true)
                match.bowler.numberOfBalls = 0
                if (match.updateOvers() == 4) {
                    gameover = true
                    return
                }
            }
            val scoreResult = match.bowler.bowl()
            ballOutput.postValue(scoreResult)
            when (scoreResult) {
                BallOutput.SINGLE,
                BallOutput.TRIPLE -> {
                    match.updateScores(scoreResult.runs)
                    match.swapRunner()
                }
                BallOutput.DOUBLE,
                BallOutput.FOUR,
                BallOutput.SIX -> {
                    match.updateScores(scoreResult.runs)
                }
                BallOutput.OUT -> match.newPlayer()
                BallOutput.WIDE -> match.updateExtras(scoreResult.runs)
            }
            val playerList = match.getClonedList()
            totalUpdate.postValue(
                Pair(
                    "SCORE: " + match.totalScore+"/"+playerList.filter { it.status == BatsmanStatus.OUT }.count(),
                    "OVERS:" + match.totalOvers + "." + match.bowler.numberOfBalls
                )
            )
            playersScoreList.postValue(playerList)
        }
    }
}