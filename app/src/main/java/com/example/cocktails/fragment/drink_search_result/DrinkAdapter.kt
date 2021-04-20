package com.example.cocktails.fragment.drink_search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.data.Drink
import com.example.cocktails.databinding.DrinkItemBinding
import com.example.cocktails.utils.Utils

class DrinkAdapter(
    private val viewModel: DrinksViewModel
) : ListAdapter<Drink, DrinkAdapter.DrinkViewHolder>(DrinkDiffUtils()) {

    class DrinkViewHolder (private val binding: DrinkItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: Drink) {
            binding.drink = drink
        }

        companion object {
            fun from(parent: ViewGroup) : DrinkViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DrinkItemBinding.inflate(layoutInflater, parent, false)
                return DrinkViewHolder(binding)
            }
        }
    }

    class DrinkDiffUtils: DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = getItem(position)
        holder.bind(drink)
        holder.itemView.setOnClickListener {
            Utils.logger("DrinkAdapter","Drink ID: ${drink.idDrink}")
            viewModel.getDetails(drinkId = drink.idDrink)
        }
    }

}
