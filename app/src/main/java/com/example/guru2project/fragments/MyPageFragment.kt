package com.example.guru2project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.guru2project.R
import com.example.guru2project.databinding.FragmentHomeBinding
import com.example.guru2project.databinding.FragmentMyPageBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_my_page,container,false)


        //쓰기 페이지
        binding.writeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_writeFragment)
        }

        //첼린지 페이지
        binding.challengeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_challengeFragment)
        }

        //검색 페이지
        binding.searchTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_searchFragment)
        }

        //홈 페이지
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)
        }

        return binding.root
    }

}