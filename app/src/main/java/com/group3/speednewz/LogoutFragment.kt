package com.group3.speednewz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LogoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        UserSession.isLoggedIn = false
        UserSession.username = ""
        this.context?.startActivity(Intent(this.context, MainActivity::class.java))
        //Complete and destroy login activity once successful
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }
}