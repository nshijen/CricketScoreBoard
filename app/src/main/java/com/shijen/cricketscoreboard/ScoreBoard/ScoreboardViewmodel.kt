package com.shijen.cricketscoreboard.ScoreBoard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shijen.cricketscoreboard.Entities.Match
import com.shijen.cricketscoreboard.Entities.Players
import com.shijen.cricketscoreboard.Entities.ScoreResult

class ScoreboardViewmodel : ViewModel() {

    var match: Match = Match()
    var oversUpdate: MutableLiveData<StringBuilder> = MutableLiveData()
    var totalUpdate: MutableLiveData<Pair<String, String>> = MutableLiveData()
    val overUpdateString: StringBuilder = StringBuilder()
    val playersScoreList: MutableLiveData<List<Players>> = MutableLiveData()
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
            when (scoreResult) {
                ScoreResult.SINGLE,
                ScoreResult.TRIPLE -> {
                    match.updateScores(scoreResult.runs)
                    match.swapRunner()
                }
                ScoreResult.DOUBLE,
                ScoreResult.FOUR,
                ScoreResult.SIX -> {
                    match.updateScores(scoreResult.runs)
                }
                ScoreResult.OUT -> match.newPlayer()
                ScoreResult.WIDE -> match.updateExtras(scoreResult.runs)
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