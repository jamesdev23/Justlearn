package com.justlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.justlearn.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the CallbackManager
        callbackManager = CallbackManager.Factory.create()

        // Set up Facebook Login Button

        binding.fbloginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                // Handle success
                Log.d(TAG, "Facebook token: ${result?.accessToken?.token}, ${result?.accessToken?.userId}, ${result?.accessToken?.applicationId}, ${result?.accessToken?.source}")
                toast("Facebook token: ${result?.accessToken?.token}, ${result?.accessToken?.userId}, ${result?.accessToken?.applicationId}, ${result?.accessToken?.source}")
            }

            override fun onCancel() {
                // Handle cancel
                Log.d(TAG, "Facebook onCancel")
                toast("Facebook onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "Facebook onError: ${error.message}")
                toast("Facebook onError")
            }

        })

//        binding.facebookButton.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        binding.googleButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.emailSignupButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun toast(message: String){
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "LOGIN ACTIVITY"
    }
}