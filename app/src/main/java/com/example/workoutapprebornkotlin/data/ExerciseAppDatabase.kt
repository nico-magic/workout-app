package com.example.workoutapprebornkotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutapprebornkotlin.model.Exercise

@Database(entities = [Exercise::class], version = 1, exportSchema = true)


abstract class ExerciseAppDatabase : RoomDatabase(){

    abstract fun exerciseDao(): ExerciseDao

    companion object{

        @Volatile
        private var INSTANCE: ExerciseAppDatabase? = null
        fun getDatabase(context: Context) : ExerciseAppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseAppDatabase::class.java,
                    "workout_app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}