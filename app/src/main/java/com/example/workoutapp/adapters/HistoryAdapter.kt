package com.example.workoutapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ItemOfRecyclerViewHistoryBinding

class HistoryAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(val binding : ItemOfRecyclerViewHistoryBinding): RecyclerView.ViewHolder(binding.root) {

        val number = binding.textViewNumber
        val date = binding.textViewDate

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ItemOfRecyclerViewHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val date : String = list.get(position)
        holder.number.text = (position + 1).toString()
        holder.date.text = date


    }
}