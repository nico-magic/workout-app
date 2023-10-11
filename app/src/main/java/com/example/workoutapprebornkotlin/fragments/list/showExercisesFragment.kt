package com.example.workoutapprebornkotlin.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapprebornkotlin.R
import com.example.workoutapprebornkotlin.viewmodel.WorkoutAppViewModel
import kotlinx.android.synthetic.main.fragment_show_exercises.view.*

class showExercisesFragment : Fragment() {

    private lateinit var mWorkoutAppViewModel: WorkoutAppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_exercises, container, false)
        val adapter = ExerciseListAdapter()
        val recyclerView = view.exerciseRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mWorkoutAppViewModel = ViewModelProvider(this).get(WorkoutAppViewModel::class.java)
        mWorkoutAppViewModel.readAllData.observe(viewLifecycleOwner, Observer {exercise ->
            adapter.setData(exercise)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_showExercisesFragment_to_addExerciseFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllExercises()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllExercises() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mWorkoutAppViewModel.deleteAllExercises()
            Toast.makeText(requireContext(), "Successfully deleted all", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete every exercise?")
        builder.setMessage("Are you sure you want to delete every exercise?")
        builder.create().show()
    }


}