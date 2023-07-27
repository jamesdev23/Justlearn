package com.justlearn.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.justlearn.R
import com.justlearn.databinding.FragmentExploreBinding
import com.justlearn.utils.DeviceUuidFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private lateinit var deviceUuidFactory: DeviceUuidFactory
    private var searchText: String = ""
    private var deviceId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // gets device id
        deviceUuidFactory = DeviceUuidFactory(requireContext())
        deviceId = deviceUuidFactory.getDeviceUuid().toString()
        Log.d(TAG, "Device ID: $deviceId")

        searchAvatarList(DEFAULT_SEARCH_TEXT)

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchAvatarList(query!!)
                return false
            }

        })

    }

    private fun searchAvatarList(searchText: String) {
        CoroutineScope(Dispatchers.Main).launch {
            showProgressBar()
//            getData(searchText)
            delay(2000)
            hideProgressBar()
        }
    }


    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.avatarList.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.avatarList.visibility = View.VISIBLE
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