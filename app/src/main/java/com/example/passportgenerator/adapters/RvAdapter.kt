package com.example.passportgenerator.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passportgenerator.databinding.RvItemBinding
import com.example.passportgenerator.db.MyPassport

class RvAdapter(val list:ArrayList<MyPassport>, val rvClick: RvClick): RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val rvItem: RvItemBinding): RecyclerView.ViewHolder(rvItem.root){
        fun onBind(myPassport : MyPassport){
            rvItem.rvItemName.text=myPassport.name
            rvItem.rvItemSurname.text=myPassport.surname
            rvItem.rvItemImage.setImageURI(Uri.parse(myPassport.imagePath))
            rvItem.card.setOnClickListener {
                rvClick.onClick(myPassport)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RvClick{
        fun onClick(label: MyPassport)
    }
}