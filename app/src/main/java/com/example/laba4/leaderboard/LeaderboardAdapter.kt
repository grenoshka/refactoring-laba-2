package com.example.laba4.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laba4.R
import kotlinx.android.synthetic.main.fragment_leaderboard.view.*
import kotlinx.android.synthetic.main.leader_on_board.view.*

class LeaderboardAdapter (val leaders:List<Leader>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.leader_on_board, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setNewLeader(holder as LeaderHolder, position)
    }

    override fun getItemCount(): Int = leaders.size

    fun setNewLeader(holder:LeaderHolder, position: Int){
        val curLeader = leaders[position]
        holder.name.text = curLeader.name
        holder.position.text = (position+1).toString()
        holder.points.text = curLeader.points.toString()
    }

    class LeaderHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val position: TextView = itemview.leaderPosition
        val name: TextView = itemview.leaderName
        val points:TextView = itemview.leaderPoints
    }
}