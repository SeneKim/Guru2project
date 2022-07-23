package com.example.guru2project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.guru2project.R
import com.example.guru2project.databinding.FragmentMyPageBinding
import com.example.guru2project.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)


        //쓰기 페이지
        binding.writeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_writeFragment)
        }

        //첼린지 페이지
        binding.challengeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_challengeFragment)
        }

        //마이 페이지
        binding.mypageTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_myPageFragment)
        }

        //홈 페이지
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }

        return binding.root
    }


}