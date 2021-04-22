package com.example.cocktails.fragment.ingredient_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.data.ingredient_details.Ingredient
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientDetailsViewModel @Inject constructor(
    val cocktailService: CocktailService
) : ViewModel() {

    val progress = MutableLiveData(false)
    private val _ingredientDetailState = MutableLiveData<IngredientDetailState>(IngredientDetailState.Default)
    val ingredientDetailState : LiveData<IngredientDetailState> = _ingredientDetailState

    fun getIngredientByName(ing: String) {
        progress.value = true
        viewModelScope.launch {
            val response = cocktailService.ingredientByName(ing)
            Utils.logger("IngredientDetailsViewModel", "Response is : $response")
            _ingredientDetailState.postValue(IngredientDetailState.IngredientDetails(response.ingredients[0]))
            progress.postValue(false)
        }
    }

    fun cleanState() {
        _ingredientDetailState.value = IngredientDetailState.Default
    }

}