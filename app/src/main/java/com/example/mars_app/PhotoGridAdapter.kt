package com.example.mars_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mars_app.databinding.GridViewItemBinding
import com.example.mars_app.network.MarsProperty


class PhotoGridAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<PhotoGridAdapter.MarsPropertyViewHolder>() {
    var properties=ArrayList<MarsProperty>()
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty){
            binding.property=marsProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
       val marsProperty= properties[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }
    override fun getItemCount(): Int = properties.size

    fun updateProperties(properties : ArrayList<MarsProperty>) {
        this.properties=properties
      notifyDataSetChanged()
    }

    // create onClickListener on recyclerView Item
      class OnClickListener(val clickListener:  (marsProperty: MarsProperty) -> Unit){
          fun onClick(marsProperty: MarsProperty)=clickListener(marsProperty)
      }
}