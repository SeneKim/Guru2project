package com.example.guru2project.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.guru2project.R
import com.example.guru2project.databinding.FragmentChallengeBinding
import com.example.guru2project.databinding.FragmentWriteBinding
import com.example.guru2project.login.auth.IntroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ChallengeFragment : Fragment() {

    private lateinit var binding: FragmentChallengeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_challenge, container, false)

        auth = Firebase.auth

        binding.logoutBtn.setOnClickListener {

            activity?.let {
                /*로그아웃 버튼*/
                view?.findViewById<Button>(R.id.logoutBtn)?.setOnClickListener {
                    Log.d("logout", "로그아웃 버튼 클릭")
                    auth.signOut()

                    val intent = Intent(context, IntroActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    Log.d("logout", "로그인 창 이동")

                }

            }
        }

        //쓰기 페이지
        binding.writeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_challengeFragment_to_writeFragment)
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