package com.example.cocktails.fragment.ingredient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.data.ingredients.Ingredient
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val service: CocktailService
) : ViewModel() {

    val progress = MutableLiveData(false)
    val notFound = MutableLiveData(false)
    val result = MutableLiveData<List<Ingredient>>()

    init {
        result.value?.let {

        } ?: getIngredientList()
    }

    private fun getIngredientList() {
        progress.value = true
        viewModelScope.launch {
            val response = service.getIngredients()
            Utils.logger("IngredientViewModel", "Response is $response")
            progress.postValue(false)
            result.postValue(response.drinks)
        }
    }


}