package com.example.localjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_item.view.*

class adapterph(val activity:MainActivity ,val photo:ArrayList<photoClass>) : RecyclerView.Adapter<adapterph.itemViewHolder>() {

    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {

        holder.itemView.apply {
            Glide.with(context).load(photo[position].url).override(600,200).into(imV)

            imV.setOnClickListener {
                activity.fullImage(photo[position].url)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(

            LayoutInflater.from(activity).inflate(
                R.layout.adapter_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = photo.size
}