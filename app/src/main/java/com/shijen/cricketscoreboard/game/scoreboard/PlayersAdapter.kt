package com.shijen.cricketscoreboard.game.scoreboard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shijen.cricketscoreboard.entities.BatsmanStatus
import com.shijen.cricketscoreboard.entities.Players
import com.shijen.cricketscoreboard.R

class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {
    var playerList = ArrayList<Players>();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_scoreboard, parent, false);
        return PlayersViewHolder(
            inflate
        );
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = playerList.get(position)
        holder.playerScore.setText("" + player.runs)
        holder.playerName.setText(player.name)
        when (playerList.get(position).status) {
            BatsmanStatus.RUNNER -> {
                holder.playerName.setTextColor(holder.itemView.context.resources.getColor(android.R.color.primary_text_dark))
                holder.playerStatus.setText("")
            }
            BatsmanStatus.BATSMAN -> {
                holder.playerName.setTextColor(holder.itemView.context.resources.getColor(android.R.color.primary_text_dark))
                holder.playerStatus.setText("")
                holder.playerScore.setText("" + player.runs+" *")
            }
            BatsmanStatus.NOT_YET_PLAYED -> {
                holder.playerName.setTextColor(holder.itemView.context.resources.getColor(android.R.color.tertiary_text_dark))
                holder.playerStatus.setText("")
            }
            BatsmanStatus.OUT -> {
                holder.playerName.setTextColor(holder.itemView.context.resources.getColor(R.color.material_red))
                holder.playerStatus.setText("OUT")

            }
        }
    }

    fun updatePlayers(players: List<Players>) {
        ArrayList<Players>().addAll(players)
        val playersDiffUtils =
            PlayersDiffUtils(
                playerList,
                players
            )
        val calculateDiff = DiffUtil.calculateDiff(playersDiffUtils)
        calculateDiff.dispatchUpdatesTo(this)
        playerList.clear()
        playerList.addAll(players)
    }

    class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playerName: TextView = itemView.findViewById(R.id.tv_player_name);
        var playerScore: TextView = itemView.findViewById(R.id.tv_player_score)
        var playerStatus: TextView = itemView.findViewById(R.id.tv_player_status)
    }
}