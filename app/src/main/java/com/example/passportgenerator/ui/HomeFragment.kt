package com.example.passportgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.passportgenerator.R
import com.example.passportgenerator.adapters.RvAdapter
import com.example.passportgenerator.databinding.ActivityMainBinding
import com.example.passportgenerator.databinding.FragmentHomeBinding
import com.example.passportgenerator.db.AppDataBase
import com.example.passportgenerator.db.MyPassport

class HomeFragment : Fragment(), RvAdapter.RvClick {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var appDataBase: AppDataBase
    private lateinit var list: ArrayList<MyPassport>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(layoutInflater)

        appDataBase=AppDataBase.getInstance(binding.root.context)
        list=ArrayList<MyPassport>()
        list.addAll(appDataBase.myPassportDao().getAllPassports())
        rvAdapter= RvAdapter(list, this)
        binding.myRv.adapter=rvAdapter

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.addPassportFragment)
        }

        return binding.root
    }

    override fun onClick(myPassport: MyPassport) {
        findNavController().navigate(R.id.infoPassportFragment, bundleOf("passport" to myPassport))
    }
}