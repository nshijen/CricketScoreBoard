package com.shijen.cricketscoreboard.entities

import kotlin.random.Random

class Bowler {
    var numberOfBalls:Int = 0
    var score:Int = 0;
    fun bowl():BallOutput{
        score = getRandomScore()
        val bowlResult = BallOutput.getScoreResultFromValue(score)
        if(bowlResult!=BallOutput.WIDE){
            numberOfBalls++
        }
        return bowlResult
    }
    fun getRandomScore():Int{
        return Random.nextInt(8)
    }
}