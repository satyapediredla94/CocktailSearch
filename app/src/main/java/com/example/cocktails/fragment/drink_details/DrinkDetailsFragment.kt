package com.example.cocktails.fragment.drink_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cocktails.MainActivity
import com.example.cocktails.R
import com.example.cocktails.data.search.Drink
import com.example.cocktails.databinding.FragmentDrinkDetailsBinding
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDrinkDetailsBinding

    private val viewModel: DrinkDetailsViewModel by viewModels()

    private val args: DrinkDetailsFragmentArgs by navArgs()

    companion object {
        const val TAG = "DrinkDetailsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkDetailsBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).apply {
            toolbar.visibility = View.GONE
            bottomNav.visibility = View.GONE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initArgs()
        viewModel.drinkState.observe(requireActivity(), { manageState(it) })
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(), onBackPressedCallback
        )
    }

    private fun initArgs() {
        Utils.logger(TAG, "Drink ID : ${args.drinkId}")
        if(args.drinkId.isNotEmpty()) {
            Utils.logger(TAG, "Inside not empty")
            viewModel.getDrinkById(drinkId = args.drinkId)
        }
    }

    private fun manageState(it: DrinkDetailsState) {
        when(it) {
            is DrinkDetailsState.DrinkResponse -> {
                cleanState()
                val drink = it.drink
                Glide.with(requireContext())
                    .load(drink.strDrinkThumb)
                    .placeholder(R.drawable.not_found_black)
                    .into(binding.toolbarImage)
                binding.instructions.text = drink.strInstructions
                binding.ctb?.title = drink.strDrink
                updateUiIngredients(it.drink)
            }
            else -> {}
        }
    }

    private fun cleanState() {
        viewModel.cleanState()
    }

    private fun updateUiIngredients(drink: Drink) {
        drink.strIngredient1.apply {
            if(this.isBlank()) {
                hideView(binding.ingredientOne)
            } else {
                showView(binding.ingredientOne, this)
            }
        }
        drink.strIngredient2.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientTwo)
            } else {
                showView(binding.ingredientTwo, this)
            }
        }
        drink.strIngredient3.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientThree)
            } else {
                showView(binding.ingredientThree, this)
            }
        }
        drink.strIngredient4.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientFour)
            } else {
                showView(binding.ingredientFour, this)
            }
        }
        drink.strIngredient5.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientFive)
            } else {
                showView(binding.ingredientFive, this)
            }
        }
        drink.strIngredient6.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientSix)
            } else {
                showView(binding.ingredientSix, this)
            }
        }
        drink.strIngredient7.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientSeven)
            } else {
                showView(binding.ingredientSeven, this)
            }
        }
        drink.strIngredient8.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientEight)
            } else {
                showView(binding.ingredientEight, this)
            }
        }
        drink.strIngredient9.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientNine)
            } else {
                showView(binding.ingredientNine, this)
            }
        }
        drink.strIngredient10.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientTen)
            } else {
                showView(binding.ingredientTen, this)
            }
        }
        drink.strIngredient11.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientEleven)
            } else {
                showView(binding.ingredientEleven, this)
            }
        }
        drink.strIngredient12.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientTwelve)
            } else {
                showView(binding.ingredientTwelve, this)
            }
        }
        drink.strIngredient13.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientThirteen)
            } else {
                showView(binding.ingredientThirteen, this)
            }
        }
        drink.strIngredient14.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientFourteen)
            } else {
                showView(binding.ingredientFourteen, this)
            }
        }
        drink.strIngredient15.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientFifteen)
            } else {
                showView(binding.ingredientFifteen, this)
            }
        }
        updateIngredientQuantity(drink)
    }

    private fun updateIngredientQuantity(drink: Drink) {
        drink.strMeasure1.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityOne)
            } else {
                showView(binding.ingredientQuantityOne, this)
            }
        }
        drink.strMeasure2.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityTwo)
            } else {
                showView(binding.ingredientQuantityTwo, this)
            }
        }
        drink.strMeasure3.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityThree)
            } else {
                showView(binding.ingredientQuantityThree, this)
            }
        }
        drink.strMeasure4.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityFour)
            } else {
                showView(binding.ingredientQuantityFour, this)
            }
        }
        drink.strMeasure5.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityFive)
            } else {
                showView(binding.ingredientQuantityFive, this)
            }
        }
        drink.strMeasure6.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantitySix)
            } else {
                showView(binding.ingredientQuantitySix, this)
            }
        }
        drink.strMeasure7.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantitySeven)
            } else {
                showView(binding.ingredientQuantitySeven, this)
            }
        }
        drink.strMeasure8.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityEight)
            } else {
                showView(binding.ingredientQuantityEight, this)
            }
        }
        drink.strMeasure9.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityNine)
            } else {
                showView(binding.ingredientQuantityNine, this)
            }
        }
        drink.strMeasure10.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityTen)
            } else {
                showView(binding.ingredientQuantityTen, this)
            }
        }
        drink.strMeasure11.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityEleven)
            } else {
                showView(binding.ingredientQuantityEleven, this)
            }
        }
        drink.strMeasure12.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityTwelve)
            } else {
                showView(binding.ingredientQuantityTwelve, this)
            }
        }
        drink.strMeasure13.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityThirteen)
            } else {
                showView(binding.ingredientQuantityThirteen, this)
            }
        }
        drink.strMeasure14.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityFourteen)
            } else {
                showView(binding.ingredientQuantityFourteen, this)
            }
        }
        drink.strMeasure15.apply {
            if(this.isNullOrEmpty()) {
                hideView(binding.ingredientQuantityFifteen)
            } else {
                showView(binding.ingredientQuantityFifteen, this)
            }
        }


    }

    private fun showView(ingredient: TextView, value: String) {
        ingredient.text = value
        ingredient.setTextColor(resources.getColor(R.color.black, null))
    }

    private fun hideView(ingredient: TextView) {
        ingredient.visibility = View.GONE
    }
}