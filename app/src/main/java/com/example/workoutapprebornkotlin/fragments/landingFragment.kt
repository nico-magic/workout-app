package com.example.workoutapprebornkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.workoutapprebornkotlin.R
import kotlinx.android.synthetic.main.fragment_landing.view.*
import kotlinx.android.synthetic.main.fragment_show_exercises.view.*

class landingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

        view.button3.setOnClickListener{
            findNavController().navigate(R.id.action_landingFragment_to_startWorkoutFragment)
        }

        return view
    }
}