package com.example.cocktails.fragment.ingredient_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.util.Util
import com.example.cocktails.MainActivity
import com.example.cocktails.MainActivity_GeneratedInjector
import com.example.cocktails.R
import com.example.cocktails.data.ingredient_details.Ingredient
import com.example.cocktails.databinding.FragmentIngredientDetailsBinding
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientDetailsFragment : Fragment() {

    private val viewModel: IngredientDetailsViewModel by viewModels()
    private lateinit var binding: FragmentIngredientDetailsBinding
    private val args: IngredientDetailsFragmentArgs by navArgs()

    companion object{
        const val TAG = "IngredientDetailsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientDetailsBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).bottomNav.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.ingredientDetailState.observe(viewLifecycleOwner, { manageState(it) })
    }

    private fun manageState(it: IngredientDetailState) {
        when(it) {
            is IngredientDetailState.IngredientDetails -> {
                cleanState()
                val ingredient = it.ingredient
                updateUI(ingredient)
            }
            else -> {}
        }
    }

    private fun updateUI(ingredient: Ingredient) {
        (requireActivity() as MainActivity).toolbar.title = ingredient.strIngredient
        if(ingredient.strABV.isNullOrEmpty()) {
            Utils.logger(TAG, "Hiding ABV Layout")
            binding.abvLayout.visibility = View.GONE
        } else {
            Utils.logger(TAG, "ABV : ${ingredient.strABV}")
            binding.abvTv.text = ingredient.strABV
        }

        if(ingredient.strAlcohol.isNullOrEmpty()) {
            Utils.logger(TAG, "Hiding Alcohol Layout")
            binding.alcoholicLayout.visibility = View.GONE
        } else {
            Utils.logger(TAG, "Alcohol : ${ingredient.strAlcohol}")
            binding.alcTv.text = ingredient.strAlcohol
        }

        if(ingredient.strType.isNullOrEmpty()) {
            Utils.logger(TAG, "Hiding Alc Type Layout")
            binding.alcoholType.visibility = View.GONE
        } else {
            Utils.logger(TAG, "Type : ${ingredient.strType}")
            binding.alcTypeTv.text = ingredient.strType
        }

        if(ingredient.strDescription.isNullOrEmpty()) {
            Utils.logger(TAG, "Hiding Desc Card Layout")
            binding.descTv.visibility = View.GONE
            binding.ingDescLayout.visibility = View.GONE
        } else {
            Utils.logger(TAG, "Desc : ${ingredient.strDescription}")
            binding.descTv.text = ingredient.strDescription
        }

        if(
            ingredient.strABV.isNullOrEmpty() &&
            ingredient.strAlcohol.isNullOrEmpty() &&
            ingredient.strType.isNullOrEmpty() &&
            ingredient.strDescription.isNullOrEmpty()
        ) {
            showNotFound()
        }
    }

    private fun cleanState() {
        viewModel.cleanState()
    }


    private fun initArgs() {
        hideNotFound()
        Utils.logger(TAG, "Args received: ${args.ingredient}")
        if(args.ingredient.isNotEmpty()) {
            viewModel.getIngredientByName(args.ingredient)
        }
    }
    
    private fun hideNotFound() {
        binding.noResultsFound.visibility = View.GONE
        binding.textInside.visibility = View.GONE
    }

    private fun showNotFound() {
        binding.noResultsFound.visibility = View.VISIBLE
        binding.textInside.visibility = View.VISIBLE
    }

}