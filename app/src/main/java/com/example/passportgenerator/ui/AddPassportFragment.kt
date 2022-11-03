package com.example.passportgenerator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.passportgenerator.databinding.FragmentAddPassportBinding
import com.example.passportgenerator.db.AppDataBase
import com.example.passportgenerator.db.MyPassport
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddPassportFragment : Fragment() {
    private lateinit var binding: FragmentAddPassportBinding
    private lateinit var uriPath:String
    private lateinit var appDataBase: AppDataBase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAddPassportBinding.inflate(layoutInflater)

        appDataBase=AppDataBase.getInstance(binding.root.context)

        uriPath=""

        binding.addImg.setOnClickListener {
            getImageContent.launch("image/*")
        }

        binding.save.setOnClickListener {
            val ch1=Random().nextInt(26)
            val ch2=Random().nextInt(26)
            val list=ArrayList<Char>()
            for (i in 'A'..'Z'){
                list.add(i)
            }
            var passportNumber="${list[ch1-1]}${list[ch2-1]}"
            while (passportNumber.length<9){
                passportNumber="$passportNumber${Random().nextInt(9)}"
            }
            if (uriPath!="" && binding.name.text.toString()!="" && binding.surname.text.toString()!="" && binding.bfd.text.toString()!=""){
                val myPassport=MyPassport(
                    name = binding.name.text.toString(),
                    surname = binding.surname.text.toString(),
                    imagePath = uriPath,
                    BOD = binding.bfd.text.toString(),
                    passportNumber = passportNumber
                )
                appDataBase.myPassportDao().addPassport(myPassport)
                findNavController().popBackStack()
            }else{
                Toast.makeText(binding.root.context, "Iltimos hamma maydonlarni toldiring.", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }


    val getImageContent=
        registerForActivityResult(ActivityResultContracts.GetContent()){
            it?:return@registerForActivityResult
            binding.addImg.setImageURI(it)
            val inputStream=requireActivity().contentResolver.openInputStream(it)
            val title=SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
            val file=File(requireActivity().filesDir, "$title.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            uriPath = file.absolutePath
        }
}