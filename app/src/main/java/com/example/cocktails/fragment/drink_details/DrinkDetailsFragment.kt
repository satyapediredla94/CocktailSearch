package com.example.cocktails.fragment.drink_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentDrinkDetailsBinding
import com.example.cocktails.utils.AppConstants
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val TAG = "DrinkDetailsFragment"

@AndroidEntryPoint
class DrinkDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDrinkDetailsBinding

    private val viewModel: DrinkDetailsViewModel by viewModels()

    private val args: DrinkDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initArgs()
        viewModel.drinkState.observe(requireActivity(), { manageState(it) })
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
                binding.ctb.title = drink.strDrink
            }
            else -> {}
        }
    }

    private fun cleanState() {
        viewModel.cleanState()
    }

}