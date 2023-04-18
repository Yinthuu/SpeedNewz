package com.group3.speednewz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.group3.speednewz.models.NewsData
import com.group3.speednewz.models.UsersData

class ProfileFragment : Fragment() {


    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val etPersonName :EditText = view.findViewById<EditText>(R.id.editTextPersonName);
        val etPassword :EditText = view.findViewById<EditText>(R.id.editTextPassword);

        etPersonName.setText(UserSession.username)
        etPassword.setText("John")

        val db = requireActivity().openOrCreateDatabase("SpeedyNewz", Context.MODE_PRIVATE, null)
        val cursor = db.rawQuery("SELECT * FROM users", null)
        val usersList = mutableListOf<UsersData>()


        if (cursor.moveToFirst()) {
            do {

                val id = cursor.getInt(cursor.getColumnIndex("userId"))
                val username = cursor.getString(cursor.getColumnIndex("username"))
                val password = cursor.getString(cursor.getColumnIndex("password"))
                val users = UsersData(id, username, password)
                usersList.add(users)
            } while (cursor.moveToNext())
        }



        // Find the button in the layout
        val button = view.findViewById<Button>(R.id.button_update)
        val updateConfirmationTextView = view.findViewById<TextView>(R.id.textView_update_confirmation)

        // Set up click listener for the button
        button.setOnClickListener {
            UserSession.username = etPersonName.text.toString()
            // Show a toast message
            Toast.makeText(requireContext(), getString(R.string.update_confirmation) , Toast.LENGTH_SHORT).show()
            updateConfirmationTextView.text = getString(R.string.update_confirmation)
        }


        cursor.close()
        db.close()

        return view
    }
}