package com.example.workoutapprebornkotlin.repository

import androidx.lifecycle.LiveData
import com.example.workoutapprebornkotlin.data.ExerciseDao
import com.example.workoutapprebornkotlin.model.Exercise

class WorkoutAppRepository(private val exerciseDao: ExerciseDao) {

    val readAllDAta: LiveData<List<Exercise>> = exerciseDao.readAllData()

    suspend fun addExercise(exercise: Exercise){
        exerciseDao.addExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise){
        exerciseDao.updateExercise(exercise)
    }

    suspend fun deleteExercise(exercise: Exercise){
        exerciseDao.deleteExercise(
            exercise
        )
    }

    suspend fun deleteAllExercises(){
        exerciseDao.deleteAllExercises()
    }
}