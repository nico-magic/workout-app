package com.example.workoutapprebornkotlin.fragments.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutapprebornkotlin.R
import com.example.workoutapprebornkotlin.data.BArrtoBitmap
import com.example.workoutapprebornkotlin.fragments.list.ExerciseListAdapter
import com.example.workoutapprebornkotlin.model.Exercise
import com.example.workoutapprebornkotlin.viewmodel.WorkoutAppViewModel
import kotlinx.android.synthetic.main.exercise_custom_row.*
import kotlinx.android.synthetic.main.fragment_start_workout.*
import kotlinx.android.synthetic.main.fragment_start_workout.view.*

class StartWorkoutFragment : Fragment() {

    private lateinit var mWorkoutAppViewModel: WorkoutAppViewModel

    private var listOfExercises = ArrayList<Exercise>()
    private var iterator = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_start_workout, container, false)
        val adapter = ExerciseListAdapter()
        mWorkoutAppViewModel = ViewModelProvider(this).get(WorkoutAppViewModel::class.java)


        mWorkoutAppViewModel.readAllData.observe(viewLifecycleOwner, Observer {exercise ->
            adapter.setData(exercise)
            listOfExercises.addAll(exercise)
            view.currentExerciseNumber_textView.text = "1";
            view.totalExerciseNumber_textView.text = (listOfExercises.size.toString())
            view.currentExerciseName_textView.text = listOfExercises[iterator].exercise_name
            currentExercise_imageView.setImageBitmap(listOfExercises[iterator].exercise_image?.let { it1 ->
                BArrtoBitmap(it1)
            })
        })

        view.nextExercise_button.setOnClickListener {
            if (iterator < listOfExercises.size - 1) {
                iterator++
            }
                currentExerciseName_textView.text = listOfExercises[iterator].exercise_name
                if (listOfExercises[iterator].exercise_image != null){
                    currentExercise_imageView.setImageBitmap(listOfExercises[iterator].exercise_image?.let { it1 ->
                        BArrtoBitmap(it1)
                    })
                } else {
                    currentExercise_imageView.setImageBitmap(null)
                }
                currentExerciseNumber_textView.text = (iterator+1).toString()
        }


        view.previousExercise_button.setOnClickListener {
            if (iterator >= 1) {
                iterator--
            }
                currentExerciseName_textView.text = listOfExercises[iterator].exercise_name
                if (listOfExercises[iterator].exercise_image != null){
                    currentExercise_imageView.setImageBitmap(listOfExercises[iterator].exercise_image?.let { it1 ->
                        BArrtoBitmap(it1)
                    })
                } else {
                    currentExercise_imageView.setImageBitmap(null)
                }
                currentExerciseNumber_textView.text = (iterator+1).toString()
        }

        if (listOfExercises.size > 0) {
            view.currentExerciseNumber_textView.text = listOfExercises[iterator].exercise_name
            view.currentExercise_imageView.setImageBitmap(listOfExercises[iterator].exercise_image?.let {
                BArrtoBitmap(
                    it
                )
            })
        }


        return view
    }

}