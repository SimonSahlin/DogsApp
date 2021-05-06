package com.example.dogsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.R
import com.example.dogsapp.model.DogsApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_rv_layout.view.*


class DogsAdapter (val context: Context?, private val dogsImages: ArrayList<DogsApi>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Binding Views here
        Picasso.get().load(dogsImages[position].message).into(holder.itemView.dogImage)
    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }


    class ViewHolder(v:View?) : RecyclerView.ViewHolder(v!!),View.OnClickListener{
        override fun onClick(v: View?) {
            //Onlick function here

        }
        init {
            itemView.setOnClickListener(this)
        }

        val dogImage = itemView.dogImage!!

    }

}