package com.justlearn.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.justlearn.databinding.FragmentChatsBinding
import com.justlearn.utils.DeviceUuidFactory
import java.net.HttpURLConnection

class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var deviceUuidFactory: DeviceUuidFactory
    private var deviceId: String = ""
    private var hasNoResult: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()

    }



    companion object {
        private const val TAG = "CHAT FRAGMENT"
        private const val KEY = "tacz0ivakq"
    }
}