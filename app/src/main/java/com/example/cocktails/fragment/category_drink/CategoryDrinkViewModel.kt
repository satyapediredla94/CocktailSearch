package com.example.cocktails.fragment.category_drink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.data.drink_category.CategoryDrink
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CategoryDrinkViewModel @Inject constructor(
    private val cocktailService: CocktailService
) : ViewModel() {

    val progress = MutableLiveData(false)
    val result = MutableLiveData<List<CategoryDrink>>()

    private val _categoryState = MutableLiveData<CategoryDrinkState>(CategoryDrinkState.Default)
    val categoryState : LiveData<CategoryDrinkState> = _categoryState

    fun getDrinksByCategory(category: String) {
        progress.value = true
        viewModelScope.launch {
            try {
                val response = cocktailService.cocktailSearchByCategory(category)
                Utils.logger("CategoryDrinkViewModel", "Response is $response")
                result.postValue(response.drinks)
            } catch (e: Exception) {
                _categoryState.postValue(CategoryDrinkState.Error)
            }
            progress.postValue(false)
        }
    }

    fun navigateToDrinkDetails(drinkId: String) {
        _categoryState.value = CategoryDrinkState.NavigateToDrinkDetails(drinkId)
    }

    fun cleanState() {
        _categoryState.value = CategoryDrinkState.Default
    }

}