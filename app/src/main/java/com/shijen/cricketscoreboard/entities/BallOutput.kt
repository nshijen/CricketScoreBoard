package com.shijen.cricketscoreboard.entities

enum class BallOutput{
    NO_RUNS(0,"NO RUNS",0),
    SINGLE(1,"SINGLE",1),
    DOUBLE(2,"DOUBLES",2),
    TRIPLE(3,"TRIPLES",3),
    FOUR(4,"FOURR !",4),
    WIDE(5,"WIDE",1),
    SIX(6,"SIXERR !",6),
    OUT(7,"OUT",0);

    var value:Int
    var runs:Int
    var resultString:String

    constructor(value:Int,result:String,runs:Int){
        this.value = value
        this.runs = runs
        this.resultString = result;
    }

    companion object{
        fun getScoreResultFromValue(value:Int):BallOutput{
            for(x in BallOutput.values()){
                if(x.value == value){
                    return x
                }
            }
            return NO_RUNS
        }
    }
}