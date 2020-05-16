package com.shijen.cricketscoreboard.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shijen.cricketscoreboard.entities.Match
import com.shijen.cricketscoreboard.entities.Players
import com.shijen.cricketscoreboard.entities.BallOutput

class GameViewModel : ViewModel() {

    var match: Match = Match()
    var oversUpdate: MutableLiveData<StringBuilder> = MutableLiveData()
    var totalUpdate: MutableLiveData<Pair<String, String>> = MutableLiveData()
    val playersScoreList: MutableLiveData<List<Players>> = MutableLiveData()
    val ballOutput: MutableLiveData<BallOutput> = MutableLiveData()
    val overUpdateString: StringBuilder = StringBuilder()
    var gameover = false;

    fun bowl() {
        if (!gameover) {
            if (match.bowler.numberOfBalls == 6) {
                match.swapRunner()
                match.bowler.numberOfBalls = 0
                overUpdateString.clear();
                if (match.updateOvers() == 4) {
                    overUpdateString.appendln("GAME OVER")
                    oversUpdate.postValue(overUpdateString)
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
            overUpdateString.appendln(scoreResult.resultString)
            oversUpdate.postValue(overUpdateString)
            totalUpdate.postValue(
                Pair(
                    "TOTAL SCORE: " + match.totalScore,
                    "OVERS:" + match.totalOvers + "." + match.bowler.numberOfBalls
                )
            )
            playersScoreList.postValue(match.getClonedList())
        }
    }
}