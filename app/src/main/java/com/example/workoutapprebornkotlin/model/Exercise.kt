package com.example.workoutapprebornkotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "exercises")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val exercise_name: String,
    val exercise_image: ByteArray?
): Parcelable