package com.example.cocktails.fragment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentSearchBinding
import com.example.cocktails.databinding.FragmentSearchBindingImpl
import com.example.cocktails.fragment.drinks.DrinkAdapter
import com.example.cocktails.fragment.drinks.DrinksViewModel
import com.example.cocktails.utils.AppConstants
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewmodel : DrinksViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        viewmodel.searchState.observe(requireActivity(), { manageState(it) })
    }

    private fun initRecyclerView() {
        binding.drinkResult.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = DrinkAdapter(viewmodel)
        }
    }

    private fun manageState(searchState: SearchState) {
        when(searchState) {
            is SearchState.GetDrinkDetails -> {
                cleanState()
                goToDrinkDetailsFragment(searchState.drinkId)
            }
            else -> {}
        }
    }

    private fun goToDrinkDetailsFragment(drinkId: String) {
        Utils.logger("SearchFragment", "Drink ID Sent is : $drinkId")
        val action = SearchFragmentDirections.actionSearchFragmentToDrinkDetailsFragment(drinkId)
        findNavController().navigate(action)
    }

    private fun cleanState() {
        viewmodel.cleanState()
    }

}