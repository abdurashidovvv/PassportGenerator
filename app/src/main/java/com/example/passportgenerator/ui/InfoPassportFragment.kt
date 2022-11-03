package com.example.passportgenerator.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passportgenerator.R
import com.example.passportgenerator.databinding.FragmentInfoPassportBinding
import com.example.passportgenerator.db.MyPassport

class InfoPassportFragment : Fragment() {
    private lateinit var binding: FragmentInfoPassportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentInfoPassportBinding.inflate(layoutInflater)

        val myPassport=arguments?.getSerializable("passport") as MyPassport

        binding.name.text=myPassport.name
        binding.surname.text=myPassport.surname
        binding.bof.text=myPassport.BOD
        binding.passportNumber.text=myPassport.passportNumber
        binding.image.setImageURI(Uri.parse(myPassport.imagePath))

        return binding.root
    }

}