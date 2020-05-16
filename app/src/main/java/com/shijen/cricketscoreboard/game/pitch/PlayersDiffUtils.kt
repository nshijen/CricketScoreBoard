package com.shijen.cricketscoreboard.game.pitch

import androidx.recyclerview.widget.DiffUtil
import com.shijen.cricketscoreboard.entities.Players

class PlayersDiffUtils : DiffUtil.Callback {
    var oldList: ArrayList<Players>
    var newList: ArrayList<Players>

    constructor(oldList: ArrayList<Players>, newList: List<Players>) {
        this.oldList = oldList
        this.newList = newList as ArrayList<Players>
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).name.equals(newList.get(newItemPosition).name)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).runs == newList.get(newItemPosition).runs
                && oldList.get(oldItemPosition).status == newList.get(newItemPosition).status
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}