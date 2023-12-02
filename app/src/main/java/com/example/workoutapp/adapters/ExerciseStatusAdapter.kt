package com.example.workoutapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.models.ExerciseModel
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ItemOfRecycleViewBinding

class ExerciseStatusAdapter(private val list : ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ExerciseViewHolder>() {
    inner class ExerciseViewHolder(viewBinding: ItemOfRecycleViewBinding): RecyclerView.ViewHolder(viewBinding.root) {


        val item = viewBinding.textViewItem


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(ItemOfRecycleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.item.text = list[position].getId().toString()
        when{
            list[position].getIsCompleted()->{

                holder.item.background = ContextCompat.getDrawable(holder.item.context,
                    R.drawable.accent_background
                )
                holder.item.setTextColor(Color.parseColor("#FFFFFF"))
            }
            list[position].getIsSelected()->{
                holder.item.background = ContextCompat.getDrawable(holder.item.context,
                    R.drawable.circular_white_background
                )
                holder.item.setTextColor(Color.parseColor("#212121"))

            }else->{
            holder.item.background = ContextCompat.getDrawable(holder.item.context,
                R.drawable.circular_grey_background
            )
            holder.item.setTextColor(Color.parseColor("#212121"))

            }

        }
    }


}


