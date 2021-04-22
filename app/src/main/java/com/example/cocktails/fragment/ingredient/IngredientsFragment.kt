package com.example.cocktails.fragment.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.MainActivity
import com.example.cocktails.databinding.FragmentIngredientsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding

    private val viewmodel: IngredientViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).apply {
            toolbar.visibility = View.VISIBLE
            bottomNav.visibility = View.VISIBLE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        initAdapter()
        viewmodel.ingredientState.observe(viewLifecycleOwner, { manageState(it) })
    }

    private fun manageState(it: IngredientState) {
        when(it) {
            is IngredientState.NavigateToIngredientDetails -> {
                cleanState()
                navigateToIngredientDetails(it.ingredient)
            }
            else -> {}
        }
    }

    private fun navigateToIngredientDetails(ingredient: String) {
        val action = IngredientsFragmentDirections.actionIngredientsFragmentToIngredientDetailsFragment(ingredient)
        findNavController().navigate(action)
    }

    private fun cleanState() {
        viewmodel.cleanState()
    }

    private fun initAdapter() {
        binding.drinkResult.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = IngredientAdapter(viewmodel)
        }
    }

}