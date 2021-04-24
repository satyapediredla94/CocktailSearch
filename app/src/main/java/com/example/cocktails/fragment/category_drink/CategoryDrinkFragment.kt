package com.example.cocktails.fragment.category_drink

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.MainActivity
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentCategoryDrinkBinding
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDrinkFragment : Fragment() {

    private val viewModel : CategoryDrinkViewModel by viewModels()
    private lateinit var binding: FragmentCategoryDrinkBinding
    private val args : CategoryDrinkFragmentArgs by navArgs()

    companion object {
        const val TAG = "CategoryDrinkFragment"
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Utils.logger("CategoryDrinkFragment", "Navigating to drinks fragment")
            navigateToDrinksFragment()
//                else NavHostFragment.findNavController(this@CategoryDrinkFragment).navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDrinkBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).apply {
            toolbar.visibility = View.VISIBLE
            bottomNav.visibility = View.GONE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initArgs()
        initAdapter()
        viewModel.categoryState.observe(requireActivity(), { manageState(it) })
        backPressListener()
    }

    private fun backPressListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(), onBackPressedCallback
        )
        if(requireActivity().onNavigateUp()) {
            Utils.logger("CategoryDrinkFragment", "On Navigate Up")
            navigateToDrinksFragment()
        }
    }

    private fun navigateToDrinksFragment() {
        findNavController().navigate(R.id.drinksFragment)
    }

    private fun manageState(it: CategoryDrinkState) {
        when(it) {
            is CategoryDrinkState.NavigateToDrinkDetails -> {
                cleanState()
                navigateToDrinkDetails(it.drinkId)
            }
            is CategoryDrinkState.Error -> {
                cleanState()
                if(!(requireActivity() as MainActivity).isNetworkAvailable()) {
                        binding.drinkResult.visibility = View.GONE
                        binding.noResultsFound.visibility = View.VISIBLE
                        binding.textInside.visibility = View.VISIBLE
                }
            }
            else -> {
            }
        }
    }

    private fun initArgs() {
            Utils.logger(TAG, "Drink ID : ${args.category}")
            if(args.category.isNotEmpty()) {
                Utils.logger(TAG, "Inside not empty")
                viewModel.getDrinksByCategory(category = args.category)
            }
    }

    private fun initAdapter() {
        binding.drinkResult.apply {
            layoutManager = if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridLayoutManager(requireActivity(), 2)
            } else {
                LinearLayoutManager(requireActivity())
            }
            adapter = CategoryDrinkAdapter(viewModel)
        }
    }

    private fun navigateToDrinkDetails(drinkId: String) {
        val action = CategoryDrinkFragmentDirections.actionCategoryDrinkFragmentToDrinkDetailsFragment(drinkId)
        findNavController().navigate(action)
    }

    private fun cleanState() {
        viewModel.cleanState()
    }

    override fun onDestroy() {
        super.onDestroy()
        //unregister listener here
        onBackPressedCallback.isEnabled = false
        onBackPressedCallback.remove()
    }

}