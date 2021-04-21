package com.example.cocktails.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.data.search.Drink
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cocktailService: CocktailService
) : ViewModel() {

    companion object {
        const val TAG = "DrinksViewModel"
    }

    val name = MutableLiveData("")
    val result = MutableLiveData<List<Drink>>()
    val progress = MutableLiveData(false)
    val notFound = MutableLiveData(false)

    private val _searchState = MutableLiveData<SearchState>(SearchState.Default)
    val searchState: LiveData<SearchState> = _searchState

    fun getCocktailByName() {
        val drinkName = name.value
        hideNotFoundFromUIThread()
        Utils.logger(TAG, "drink value is  $drinkName")
        if(drinkName!!.isNotEmpty()) {
            progress.value = true
            Utils.logger(TAG, "Inside not empty with ${progress.value}")
            viewModelScope.launch(Dispatchers.IO) {
                val response = cocktailService.cocktailByName(drinkName)
                Utils.logger(TAG, "Response is $response")
                result.postValue(response.drinks)
                if(response.drinks == null) {
                    clearRecyclerViewFromBT()
                    showNotFoundFromBT()
                }
                Utils.logger(TAG, "Response is ${result.value}")
                progress.postValue(false)
            }
        }
    }

    fun getDetails(drinkId: String) {
        _searchState.value = SearchState.GetDrinkDetails(drinkId)
    }

    private fun clearRecyclerViewFromBT() {
        result.postValue(listOf())
    }

    private fun clearRecyclerViewFromUIThread() {
        result.value = listOf()
    }

    private fun showNotFoundFromBT() {
        notFound.postValue(true)
    }

    private fun hideNotFoundFromUIThread() {
        notFound.value = false
    }

    fun cleanState() {
        _searchState.value = SearchState.Default
    }

}