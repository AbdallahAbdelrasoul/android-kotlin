package com.udacity.project4.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.udacity.project4.R
import com.udacity.project4.locationreminders.RemindersActivity

/**
 * This class should be the starting point of the app, It asks the users to sign in / register, and redirects the
 * signed in users to the RemindersActivity.
 */
class AuthenticationActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AuthenticationActivity"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private lateinit var _viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        _viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

//      If the user was authenticated, send him to RemindersActivity
        FirebaseAuth.getInstance().currentUser?.let {
            startRemindersActivityAndFinish()
        }

        val loginBtn = findViewById<Button>(R.id.login_btn).setOnClickListener {
            launchSignInFlow()
        }
        val signupBtn = findViewById<Button>(R.id.signup_btn).setOnClickListener {
            launchSignInFlow()
        }

//         a bonus is to customize the sign in flow to look nice using :
//          https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#custom-layout
    }

    private fun startRemindersActivityAndFinish(){
        val remindersIntent =
            Intent(applicationContext, RemindersActivity::class.java)
        startActivity(remindersIntent)
        finish()
        Toast.makeText(
            this,
            "Welcome ${FirebaseAuth.getInstance().currentUser?.displayName}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                startRemindersActivityAndFinish()
            } else {
                Toast.makeText(
                    this,
                    "Sign in unsuccessful ${response?.error?.errorCode}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //Implement the create account and sign in using FirebaseUI, use sign in using email and sign in using Google
    private fun launchSignInFlow() {
        // Give users the option to sign in / register with their email or Google account. If users
        // choose to register with their email, they will need to create a password as well.
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent. We listen to the response of this activity with the
        // SIGN_IN_RESULT_CODE code.
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            SIGN_IN_RESULT_CODE
        )
    }
}
//    private fun observeAuthenticationState() {
//        _viewModel.authenticationState.observe(this, Observer { authenticationState ->
//            when (authenticationState) {
//                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
//                    val remindersIntent =
//                        Intent(applicationContext, RemindersActivity::class.java)
//                    startActivity(remindersIntent)
//                    Toast.makeText(this,"Welcome ${FirebaseAuth.getInstance().currentUser?.displayName}", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
//                else -> {
//                    launchSignInFlow()
//                }
//
//            }
//        })
//    }