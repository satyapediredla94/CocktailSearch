package com.example.cocktails.fragment.drink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.data.category.Drink
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val cocktailService: CocktailService
) : ViewModel() {

    val progress = MutableLiveData(false)
    val result = MutableLiveData<List<Drink>>()

    private val _state = MutableLiveData<DrinkState>(DrinkState.Default)
    val drinkState : LiveData<DrinkState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        progress.value = true
        viewModelScope.launch {
            val response = cocktailService.getCategories()
            Utils.logger("DrinkViewModel", "Response is $response")
            result.value = response.drinks
            progress.postValue(false)
        }
    }

    fun navigateToDrinksInCategory(category: String) {
        _state.value = DrinkState.NavigateToCategory(category)
    }

    fun cleanState() {
        _state.value = DrinkState.Default
    }


}