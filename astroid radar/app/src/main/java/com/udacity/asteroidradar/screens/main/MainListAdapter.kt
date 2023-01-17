package com.udacity.asteroidradar.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import com.udacity.asteroidradar.domain.Asteroid

class MainListAdapter(private val clickListener: AsteroidListener) : ListAdapter<Asteroid, MainListAdapter.ViewHolder>
    (AsteroidDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class ViewHolder private constructor(private val binding:AsteroidItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(item : Asteroid, clickListener: AsteroidListener){
                binding.asteroid = item
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }

            companion object{
                fun from(parent: ViewGroup): ViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = AsteroidItemBinding.inflate(layoutInflater,parent,false)

                    return ViewHolder(binding)
                }
            }
        }

    companion object{
        class AsteroidDiffCallback:DiffUtil.ItemCallback<Asteroid>(){
            override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                return oldItem == newItem
            }
        }

        class AsteroidListener(val clickListener: (Asteroid : Asteroid) -> Unit){
            fun onClick(asteroid: Asteroid) = clickListener(asteroid)
        }
    }
}