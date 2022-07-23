package com.example.guru2project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.guru2project.R
import com.example.guru2project.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)


        //쓰기 페이지
        binding.writeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_writeFragment)
        }

        //첼린지 페이지
        binding.challengeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_challengeFragment)
        }

        //검색 페이지
        binding.searchTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        //마이 페이지
        binding.mypageTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_myPageFragment)
        }

        return binding.root
    }


}