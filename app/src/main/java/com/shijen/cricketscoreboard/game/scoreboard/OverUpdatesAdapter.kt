package com.shijen.cricketscoreboard.game.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shijen.cricketscoreboard.R
import com.shijen.cricketscoreboard.entities.BallOutput

class OverUpdatesAdapter: RecyclerView.Adapter<OverUpdatesAdapter.OverUpdateHolder>() {
    val list:ArrayList<BallOutput> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverUpdateHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_overupdate, parent, false)
        return OverUpdateHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OverUpdateHolder, position: Int) {
        val ballOutput = list.get(position)
        if(ballOutput.equals(BallOutput.OUT)){
            holder.item.setTextColor(holder.item.context.resources.getColor(R.color.material_red))
        }else{
            holder.item.setTextColor(holder.item.context.resources.getColor(R.color.material_blue))
        }
        holder.item.setText(ballOutput.minstring)
    }

    fun addBallOutput(item:BallOutput){
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun clearList(){
        list.clear()
        notifyDataSetChanged()
    }

    class OverUpdateHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<TextView>(R.id.tv_over_output)
    }
}