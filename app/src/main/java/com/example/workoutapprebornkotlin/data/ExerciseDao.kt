package com.example.workoutapprebornkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workoutapprebornkotlin.model.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("DELETE FROM exercises")
    suspend fun deleteAllExercises()

    @Query("SELECT * FROM exercises ORDER BY id ASC")
    fun readAllData(): LiveData<List<Exercise>>
}