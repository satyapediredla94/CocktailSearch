package com.example.cocktails.fragment.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.data.ingredients.Ingredient
import com.example.cocktails.fragment.drink_details.DrinkDetailsState
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val service: CocktailService
) : ViewModel() {

    val progress = MutableLiveData(false)
    val notFound = MutableLiveData(false)
    val result = MutableLiveData<List<Ingredient>>()
    private val _ingredientState = MutableLiveData<IngredientState>(IngredientState.Default)
    val ingredientState: LiveData<IngredientState> = _ingredientState

    init {
        getIngredientList()
    }

    private fun getIngredientList() {
        progress.value = true
        viewModelScope.launch {
            try {
            val response = service.getIngredients()
            Utils.logger("IngredientViewModel", "Response is $response")
            result.postValue(response.drinks)
            } catch (e: Exception) {
                _ingredientState.postValue(IngredientState.Error)
            }
            progress.postValue(false)
        }
    }

    fun navigateToIngredientDetails(ingredient: String) {
        _ingredientState.value = IngredientState.NavigateToIngredientDetails(ingredient)
    }

    fun cleanState() {
        _ingredientState.value = IngredientState.Default
    }


}