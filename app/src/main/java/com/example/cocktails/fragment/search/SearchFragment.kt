package com.example.cocktails.fragment.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktails.MainActivity
import com.example.cocktails.databinding.FragmentSearchBinding
import com.example.cocktails.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewmodel : SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
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
        initRecyclerView()
        viewmodel.searchState.observe(requireActivity(), { manageState(it) })
    }

    private fun initRecyclerView() {
        binding.drinkResult.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = SearchDrinkAdapter(viewmodel)
        }
    }

    private fun manageState(searchState: SearchState) {
        when(searchState) {
            is SearchState.GetDrinkDetails -> {
                cleanState()
                goToDrinkDetailsFragment(searchState.drinkId)
            }
            is SearchState.HideKeyBoard -> {
                cleanState()
                hideKeyboard()
            }
            is SearchState.Error -> {
                cleanState()
                if(!(requireActivity() as MainActivity).isNetworkAvailable()) {
                    binding.drinkResult.visibility = View.GONE
                    binding.noResultsFound.visibility = View.VISIBLE
                    binding.textInside.visibility = View.VISIBLE
                }
            }
            else -> {}
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
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