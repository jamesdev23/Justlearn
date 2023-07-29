package com.justlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.justlearn.databinding.ActivityLoginBinding
import com.justlearn.utils.SharedPreferencesManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager
    private lateinit var gso: GoogleSignInOptions
    private var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoggedIn = SharedPreferencesManager.isLoggedIn(this)
        if (isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        callbackManager = CallbackManager.Factory.create()

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.google_server_client_id))
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)



        binding.facebookButton.setOnClickListener {
            performFacebookLogin()
        }


        binding.googleButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    private fun performFacebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(
            this, listOf("public_profile"))

        LoginManager.getInstance().registerCallback(callbackManager, object
            : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult) {
                Log.d(TAG, "Facebook token: ${result.accessToken.token}, ${result.accessToken.userId}, ${result.accessToken.applicationId}, ${result.accessToken.source}, ${result.accessToken.expires}")


                val userId = result.accessToken?.userId ?: ""
                val accessToken = result.accessToken?.token ?: ""

                SharedPreferencesManager.saveFacebookLoginInfo(
                    applicationContext,
                    userId ?: "",
                    accessToken ?: ""
                )

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                toast("Login Success")
            }

            override fun onCancel() {
                Log.d(TAG, "FB Cancel")
                toast("Login Cancelled by User")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "FB Error: ${error.message}")
                toast("FB Login Error")
            }
        })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            } catch (e: ApiException) {
                toast("Google Login Error")
                Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            }
        }

        if (requestCode == FB_SIGN_IN) {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email
            val idToken = account?.idToken

            Log.d(TAG, "Google token: ${account?.idToken}, ${account?.email}, ${account?.displayName}, ${account?.id}, ${account?.photoUrl}, ${account.serverAuthCode}, ${account.grantedScopes}, ${account.requestedScopes}")

            SharedPreferencesManager.saveGoogleLoginInfo(
                applicationContext,
                email ?: "",
                idToken ?: ""
            )

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
            toast("Login Success")

        } catch (e: ApiException) {
            Log.d(TAG, "signInResult:failed code=" + e.statusCode)
            toast("Google Login Error")
        }
    }

    fun toast(message: String){
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "LOGIN ACTIVITY"
        private const val RC_SIGN_IN = 9001
        private const val FB_SIGN_IN = 64206
    }
}