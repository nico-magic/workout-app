package com.example.workoutapprebornkotlin.fragments.update

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.workoutapprebornkotlin.data.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.workoutapprebornkotlin.R
import com.example.workoutapprebornkotlin.model.Exercise
import com.example.workoutapprebornkotlin.viewmodel.WorkoutAppViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.io.ByteArrayOutputStream

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mWorkoutAppViewModel: WorkoutAppViewModel
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mWorkoutAppViewModel = ViewModelProvider(this).get(WorkoutAppViewModel::class.java)

        view.updateTextExerciseName.setText(args.currentExercise.exercise_name)
        val bmp: Bitmap? = args.currentExercise.exercise_image?.let { BitmapFactory.decodeByteArray(args.currentExercise.exercise_image, 0, it.size) };
        view.updateExerciseImageButton.setImageBitmap(bmp)

        view.updateAddExerciseButton.setOnClickListener{
            updateItem()
        }

        view.updateAddExerciseImageButton.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.delete_menu, menu)
    }


    private fun updateItem(){
        val exName = updateTextExerciseName.text.toString()
        val exImage = BArrfromBitmap(updateExerciseImageButton.drawable.toBitmap())

        if (inputCheck(exName)){
            //mb works dunno
            val updatedExercise = Exercise(args.currentExercise.id, exName, null)
            mWorkoutAppViewModel.updateExercise(updatedExercise)
            val updatedExercise2 = Exercise(args.currentExercise.id, exName, exImage)
            mWorkoutAppViewModel.updateExercise(updatedExercise2)

            Toast.makeText(requireContext(), "Upadted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_showExercisesFragment)
        } else{
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheck(exName: String): Boolean{
        return !(TextUtils.isEmpty(exName))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            updateExerciseImageButton.setImageURI(imageUri)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteExercise()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteExercise() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mWorkoutAppViewModel.deleteExercise(args.currentExercise)
            Toast.makeText(requireContext(), "Successfully deleted: ${args.currentExercise.exercise_name}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_showExercisesFragment)
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete ${args.currentExercise.exercise_name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentExercise.exercise_name}?")
        builder.create().show()

    }
}