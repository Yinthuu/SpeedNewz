package com.group3.speednewz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Find the button in the layout
        val button = view.findViewById<Button>(R.id.button_update)
        val updateConfirmationTextView = view.findViewById<TextView>(R.id.textView_update_confirmation)

        // Set up click listener for the button
        button.setOnClickListener {
            // Show a toast message
            Toast.makeText(requireContext(), getString(R.string.update_confirmation) , Toast.LENGTH_SHORT).show()
            updateConfirmationTextView.text = getString(R.string.update_confirmation)
        }

        return view
    }
}