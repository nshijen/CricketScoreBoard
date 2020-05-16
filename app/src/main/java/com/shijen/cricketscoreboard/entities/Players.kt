package com.shijen.cricketscoreboard.entities

import java.io.Serializable

class Players :Serializable{
    constructor(name:String){
        this.name = name
    }
    constructor(name:String,runs:Int,status:BatsmanStatus){
        this.name = name
        this.runs = runs
        this.status = status
    }
    var name:String = ""
    var runs: Int = 0
    var noOfBalls:Int = 0
    var status:BatsmanStatus = BatsmanStatus.NOT_YET_PLAYED
}