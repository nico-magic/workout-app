package com.example.workoutapprebornkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutapprebornkotlin.data.ExerciseAppDatabase
import com.example.workoutapprebornkotlin.repository.WorkoutAppRepository
import com.example.workoutapprebornkotlin.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutAppViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Exercise>>
    private val repository: WorkoutAppRepository

    init{
        val exerciseDao = ExerciseAppDatabase.getDatabase(application).exerciseDao()
        repository = WorkoutAppRepository(exerciseDao)
        readAllData = repository.readAllDAta
    }

    fun addExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO){
            repository.addExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExercise(exercise)
        }
    }

    fun deleteAllExercises(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllExercises()
        }
    }
}