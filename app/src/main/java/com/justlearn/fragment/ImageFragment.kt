package com.justlearn.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.justlearn.R
import com.justlearn.databinding.FragmentCreateBinding
import com.justlearn.databinding.FragmentImageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            showProgressBar()
            binding.webView.apply {
                settings.javaScriptEnabled = true
                settings.cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
                webViewClient = WebViewClient()
                loadUrl("https://www.justlearn.com/settings")
            }
            delay(1000)
            hideProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.webView.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.webView.visibility = View.VISIBLE
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