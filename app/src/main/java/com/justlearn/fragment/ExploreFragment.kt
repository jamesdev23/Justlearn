package com.justlearn.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.justlearn.databinding.FragmentExploreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URLEncoder

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView
    private var siteUrl = ""
    private var newUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        siteUrl = "https://justlearn.com/"

        CoroutineScope(Dispatchers.Main).launch {
            showProgressBar()
            setupWebView()
            binding.webView.loadUrl(siteUrl)
            delay(1500)
            hideProgressBar()
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                loadUrl(query!!)
                return false
            }

        })

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.cacheMode = android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
            webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: android.webkit.WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    loadFallbackUrl()
                }
            }
        }
    }

    private fun loadUrl(url: String) {
        val encodedUrl = url.replace(" ", "-").lowercase()

        newUrl = "$siteUrl$encodedUrl"
        binding.webView.loadUrl(newUrl)
        toast("Loading...")
        newUrl = ""

    }

    private fun loadFallbackUrl() {
        val fallbackUrl = siteUrl
        loadUrl(fallbackUrl)
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

    companion object {
        private const val TAG = "ExploreFragment"
        private const val KEY = "tacz0ivakq"
        private const val DEFAULT_SEARCH_TEXT = "Assistants"
    }
}