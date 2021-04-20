package com.example.cocktails.fragment.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.data.ingredients.Ingredient
import com.example.cocktails.databinding.IngredientItemBinding
import com.example.cocktails.utils.Utils

class IngredientAdapter(
    private val viewModel: IngredientViewModel
) : ListAdapter<Ingredient, IngredientAdapter.IngredientViewHolder>(IngredientDiffUtils()) {

    class IngredientViewHolder (private val binding: IngredientItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredient) {
            binding.drink = ingredient
        }

        companion object {
            fun from(parent: ViewGroup) : IngredientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(layoutInflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }
    }

    class IngredientDiffUtils: DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.strIngredient1 == newItem.strIngredient1
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val drink = getItem(position)
        holder.bind(drink)
        holder.itemView.setOnClickListener {
            Utils.logger("DrinkAdapter","Drink ID: ${drink.strIngredient1}")
//            viewModel.getDetails(drinkId = drink.idDrink)
        }
    }

}
