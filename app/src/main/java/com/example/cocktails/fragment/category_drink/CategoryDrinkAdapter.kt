package com.example.cocktails.fragment.category_drink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.data.drink_category.CategoryDrink
import com.example.cocktails.databinding.CategoryDrinkItemBinding
import com.example.cocktails.utils.Utils

class CategoryDrinkAdapter(
    private val viewModel: CategoryDrinkViewModel
) : ListAdapter<CategoryDrink, CategoryDrinkAdapter.CategoryDrinkViewHolder>(CategoryDrinkDiffUtil()) {

    class CategoryDrinkViewHolder(val binding: CategoryDrinkItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: CategoryDrink) {
            binding.drink = drink
            Glide.with(itemView.context)
                .load(drink.strDrinkThumb)
                .placeholder(R.drawable.not_found_black)
                .centerCrop()
                .into(binding.imageDrink)
        }

        companion object {
            fun from(parent: ViewGroup) : CategoryDrinkViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CategoryDrinkItemBinding.inflate(inflater, parent, false)
                return CategoryDrinkViewHolder(binding)
            }
        }
    }

    class CategoryDrinkDiffUtil : DiffUtil.ItemCallback<CategoryDrink>() {
        override fun areItemsTheSame(oldItem: CategoryDrink, newItem: CategoryDrink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: CategoryDrink, newItem: CategoryDrink): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDrinkViewHolder {
        return CategoryDrinkViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryDrinkViewHolder, position: Int) {
        val drink = getItem(position)
        holder.bind(drink)
        holder.itemView.setOnClickListener {
            Utils.logger("CategoryDrinkAdapter", "Clicked ${drink.strDrink}")
            viewModel.navigateToDrinkDetails(drink.idDrink)
        }
    }

}