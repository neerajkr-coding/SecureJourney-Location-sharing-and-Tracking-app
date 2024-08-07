package com.example.securejourney

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberList = mutableListOf<Member>(Member("Neeraj",R.drawable.ic_home,"Delhi"))
        memberList.add(Member("Arpit",R.drawable.blue_gradient,"Mumbai"))
        memberList.add(Member("VK",R.drawable.sos,"Himachal"))
        memberList.add(Member("Vaibhav K",R.drawable.dp_icon,"Hari nagar"))
        memberList.add(Member("Vaibhav R",R.drawable.gaurd,"Mumbai"))

        val adapter = MemberAdapter(memberList)

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}