package com.example.workoutapprebornkotlin.fragments.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.workoutapprebornkotlin.R
import com.example.workoutapprebornkotlin.model.Exercise
import com.example.workoutapprebornkotlin.viewmodel.WorkoutAppViewModel
import kotlinx.android.synthetic.main.fragment_add_exercise.*
import kotlinx.android.synthetic.main.fragment_add_exercise.view.*
import com.example.workoutapprebornkotlin.data.*

// TODO: Rename parameter arguments, choose names that match

class AddExerciseFragment : Fragment() {
    private lateinit var mWorkoutAppViewModel: WorkoutAppViewModel
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_exercise, container, false)
        mWorkoutAppViewModel = ViewModelProvider(this).get(WorkoutAppViewModel::class.java)
        view.addExerciseButton.setOnClickListener{
            insertDataToDatabase()
        }

        view.addExerciseImageButton.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        return view
    }

    private fun insertDataToDatabase(){
        val exName = editTextExerciseName.text.toString()
        var image :ByteArray? = null
        if (exerciseImageButton.drawable != null){
            image = BArrfromBitmap(exerciseImageButton.drawable.toBitmap())
        }
        if (inputCheck(exName)){
            val exercise = Exercise(0, exName, image)
            mWorkoutAppViewModel.addExercise(exercise)
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addExerciseFragment_to_showExercisesFragment)
        } else {
            Toast.makeText(requireContext(), "Not Added", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(exName: String): Boolean{
        return !(TextUtils.isEmpty(exName))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            exerciseImageButton.setImageURI(imageUri)
        }
    }

}