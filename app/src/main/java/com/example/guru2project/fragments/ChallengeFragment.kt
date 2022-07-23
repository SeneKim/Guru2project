package com.example.guru2project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.guru2project.R
import com.example.guru2project.databinding.FragmentChallengeBinding
import com.example.guru2project.databinding.FragmentWriteBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ChallengeFragment : Fragment() {

    private lateinit var binding : FragmentChallengeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_challenge,container,false)


        //쓰기 페이지
        binding.writeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_writeFragment)
        }

        //마이 페이지
        binding.mypageTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_challengeFragment_to_myPageFragment)
        }

        //검색 페이지
        binding.searchTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_challengeFragment_to_searchFragment)
        }

        //홈 페이지
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_challengeFragment_to_homeFragment)
        }

        return binding.root
    }


}