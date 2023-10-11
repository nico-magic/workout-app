package com.example.workoutapprebornkotlin.fragments.list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapprebornkotlin.R
import com.example.workoutapprebornkotlin.model.Exercise
import kotlinx.android.synthetic.main.exercise_custom_row.view.*

class ExerciseListAdapter: RecyclerView.Adapter<ExerciseListAdapter.MyViewHolder>() {

    private var exerciseList = emptyList<Exercise>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = exerciseList[position]
        holder.itemView.exercise_name_text.text = currentItem.exercise_name

        if (currentItem.exercise_image != null){
            holder.itemView.exercise_image_view.setImageBitmap(
            byteArrayToBitmap(currentItem.exercise_image))
        }

        holder.itemView.exercise_custom_row.setOnClickListener{
            val action = showExercisesFragmentDirections.actionShowExercisesFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    fun setData(exercise: List<Exercise>){
        this.exerciseList = exercise
        notifyDataSetChanged()
    }

}