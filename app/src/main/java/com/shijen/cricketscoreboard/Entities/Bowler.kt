package com.shijen.cricketscoreboard.Entities

import android.util.Log
import kotlin.random.Random

class Bowler {
    var numberOfBalls:Int = 0
    var score:Int = 0;
    fun bowl():ScoreResult{
        score = getRandomScore()
        val bowlResult = ScoreResult.getScoreResultFromValue(score)
        if(bowlResult!=ScoreResult.WIDE){
            numberOfBalls++
        }
        return bowlResult
    }
    fun getRandomScore():Int{
        return Random.nextInt(8)
    }
}