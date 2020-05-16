package com.shijen.cricketscoreboard.entities

enum class BallOutput{
    NO_RUNS(0,"NO RUNS",0,"0"),
    SINGLE(1,"SINGLE",1,"1"),
    DOUBLE(2,"DOUBLES",2,"2"),
    TRIPLE(3,"TRIPLES",3,"3"),
    FOUR(4,"FOURR !",4,"4"),
    WIDE(5,"WIDE",1,"W"),
    SIX(6,"SIXERR !",6,"6"),
    OUT(7,"OUT",0,"W");

    var value:Int
    var runs:Int
    var resultString:String
    var minstring:String

    constructor(value:Int,result:String,runs:Int,minstring:String){
        this.value = value
        this.runs = runs
        this.resultString = result
        this.minstring = minstring;
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