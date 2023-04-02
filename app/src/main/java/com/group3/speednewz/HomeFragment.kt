package com.group3.speednewz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
         //Find a button by Id. Go to next page upon clicking that button
        view.findViewById<Button>(R.id.button_home)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.nav_home_to_content, null
            )
        )
        return view
    }
}