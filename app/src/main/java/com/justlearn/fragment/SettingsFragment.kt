package com.justlearn.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import com.justlearn.LoginActivity
import com.justlearn.databinding.FragmentSettingsBinding
import com.justlearn.utils.SharedPreferencesManager

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.logoutButton.setOnClickListener {
            SharedPreferencesManager.clearLoginInfo(requireContext())
            val loginActivity = Intent(activity, LoginActivity::class.java)
            startActivity(loginActivity)
            toast("Log Out")
        }
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

    }


    companion object {
        private const val TAG = "IMAGE FRAGMENT"
        private const val KEY = "tacz0ivakq"
    }
}