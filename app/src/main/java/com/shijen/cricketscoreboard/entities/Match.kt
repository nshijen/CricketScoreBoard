package com.shijen.cricketscoreboard.entities

import kotlin.collections.ArrayList

class Match() {
    var batsman1:Players
    var batsman2:Players
    var bowler:Bowler = Bowler()
    var playersList:ArrayList<Players>
    var totalScore:Int = 0
    var totalOvers:Int = 0
    var nextPlayerPos = 2

    init {
        playersList = ArrayList()
        playersList.add(Players("PLAYER 1"))
        playersList.add(Players("PLAYER 2"))
        playersList.add(Players("PLAYER 3"))
        playersList.add(Players("PLAYER 4"))
        playersList.add(Players("PLAYER 5"))
        playersList.add(Players("PLAYER 6"))
        playersList.add(Players("PLAYER 7"))
        playersList.add(Players("PLAYER 8"))
        playersList.add(Players("PLAYER 9"))
        playersList.add(Players("PLAYER 10"))
        playersList.add(Players("PLAYER 11"))
        batsman1 = playersList.get(0)
        batsman2 = playersList.get(1)
        batsman1.status = BatsmanStatus.BATSMAN
        batsman2.status = BatsmanStatus.RUNNER
    }
    fun swapRunner(){
        if(batsman1.status == BatsmanStatus.RUNNER){
            batsman1.status = BatsmanStatus.BATSMAN
            batsman2.status = BatsmanStatus.RUNNER
        }else{
            batsman1.status = BatsmanStatus.RUNNER
            batsman2.status = BatsmanStatus.BATSMAN
        }
    }
    fun updateScores(score:Int){
        val batsman = getBatsman()
        batsman.runs = batsman.runs+score
        totalScore = totalScore+score
    }
    fun updateExtras(score:Int){
        totalScore = totalScore+score
    }
    fun updateOvers():Int{
        return totalOvers++
    }
    fun getBatsman():Players{
        if(batsman1.status == BatsmanStatus.BATSMAN){
            return batsman1
        }else{
            return batsman2
        }
    }
    fun newPlayer(){
        if(batsman1.status == BatsmanStatus.BATSMAN){
            batsman1.status = BatsmanStatus.OUT
            batsman1 = playersList.get(nextPlayerPos)
            batsman1.status = BatsmanStatus.BATSMAN
        }else{
            batsman2.status = BatsmanStatus.OUT
            batsman2 = playersList.get(nextPlayerPos)
            batsman2.status = BatsmanStatus.BATSMAN
        }
        nextPlayerPos++
    }
    fun getClonedList():ArrayList<Players>{
        var playerListCopy:ArrayList<Players> = ArrayList()
        for(player in playersList){
            playerListCopy.add(Players(player.name,player.runs,player.status))
        }
        return playerListCopy;
    }
}