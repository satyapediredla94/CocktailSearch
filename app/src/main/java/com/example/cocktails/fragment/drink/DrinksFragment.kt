package com.example.cocktails.fragment.drink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.MainActivity
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentDrinksBinding
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinksFragment : Fragment() {

    private val viewModel: DrinkViewModel by viewModels()
    private lateinit var binding: FragmentDrinksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDrinksBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).toolbar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        initAdapter()
        viewModel.drinkState.observe(requireActivity(), {
            manageState(it)
        })
    }

    private fun manageState(it: DrinkState) {
        when(it) {
            is DrinkState.NavigateToCategory -> {
                cleanState()
                navigateToDrinkCategory(it.category)
            }
            else -> {}
        }
    }

    private fun navigateToDrinkCategory(category: String) {
        val action = DrinksFragmentDirections.actionDrinksFragmentToCategoryDrinkFragment(category)
        Utils.logger("DrinksFragment", "Navigating to Category Fragment")
        findNavController().navigate(action)
    }

    private fun initAdapter() {
        binding.categoryResult.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = DrinkAdapter(viewModel)
        }
    }

    private fun cleanState() {
        viewModel.cleanState()
    }

}