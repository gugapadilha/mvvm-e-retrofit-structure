package com.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmprojectstructure.R
import com.example.mvvmprojectstructure.databinding.ResItemLiveBinding
import com.models.Live

class MainAdapter(private val onItemClicked: (Live) -> Unit
) : RecyclerView.Adapter<MainViewHolder>() {

    private var lives = mutableListOf<Live>()

    @SuppressLint("NotifyDataSetChanged")
    fun setLiveList(lives: List<Live>){

        this.lives = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemLiveBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = lives[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return lives.size
    }
}

class MainViewHolder(val binding: ResItemLiveBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(live: Live, onItemClicked: (Live) -> Unit){

        binding.title.text = live.title
        binding.author.text = live.autor

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(live.thumbnailUrl)
            .into(binding.thumbnail)

        itemView.setOnClickListener{
            onItemClicked(live)
        }
    }
}