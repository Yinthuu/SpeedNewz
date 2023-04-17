package com.group3.speednewz.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.group3.speednewz.MainActivity
import com.group3.speednewz.databinding.ActivityLoginBinding

import com.group3.speednewz.R
import com.group3.speednewz.UserSession

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    private val guestLoginButton: Button by lazy {
        findViewById(R.id.login_as_guest)
    }

    private val loginButton: Button by lazy {
        findViewById(R.id.login)
    }
    private val usernameEditText: EditText by lazy {
        findViewById(R.id.username)
    }
    private val passwordEditText: EditText by lazy {
        findViewById(R.id.password)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guestLoginButton.setOnClickListener {
            UserSession.isLoggedIn = true
            UserSession.username = "Guest"
            startActivity(Intent(this, MainActivity::class.java))
            //Complete and destroy login activity once successful
            finish()
        }




        loginButton.setOnClickListener {
// Check if the list is not null and contains the entered username and password
            val loggedinUsername = usernameEditText.text.toString()
            val loggedinPassword = passwordEditText.text.toString()
//            if (usersList != null && usersList.contains(Pair(loggedinUsername, loggedinPassword))) {
//                // Login successful
//                println(" Login successful")
//            } else {
//                // Login failed
//                println(" Login failed")
//            }
        }

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
//            val prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
//            val editor = prefs.edit()
//            editor.putBoolean("isLoggedIn", true)
//            editor.putString("username", username.text.toString())
//            editor.putString("password", password.text.toString())
//            editor.apply()
            UserSession.isLoggedIn = true
            UserSession.username = username.text.toString()
            startActivity(Intent(this, MainActivity::class.java))
            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}