package com.example.cocktails.fragment.drink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.data.category.Drink
import com.example.cocktails.databinding.DrinkCategoryItemBinding
import com.example.cocktails.utils.Utils

class DrinkAdapter(
    private val viewModel: DrinkViewModel
) : ListAdapter<Drink, DrinkAdapter.DrinkViewHolder>(DrinkDiffUtil()) {

    class DrinkViewHolder(
        val binding: DrinkCategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: Drink) {
            binding.drink = drink
        }

        companion object {
            fun from(parent: ViewGroup) : DrinkViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DrinkCategoryItemBinding.inflate(inflater, parent, false)
                return DrinkViewHolder(binding)
            }
        }
    }

    class DrinkDiffUtil : DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.strCategory == newItem.strCategory
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
            Utils.logger("DrinkAdapter", "Drink Category ${drink.strCategory} clicked")
            val category = drink.strCategory.replace(" ", "_")
            Utils.logger("DrinkAdapter", "Category is : $category")
            viewModel.navigateToDrinksInCategory(category)
        }
    }

}