package com.example.cocktails.fragment.drink_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.api.CocktailService
import com.example.cocktails.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val cocktailService: CocktailService
) : ViewModel() {

    private val _drinkState = MutableLiveData<DrinkDetailsState>(DrinkDetailsState.Default)
    val drinkState : LiveData<DrinkDetailsState> = _drinkState

    val progress = MutableLiveData(false)

    fun getDrinkById(drinkId: String) {
        progress.value = true
        viewModelScope.launch {
            val response = cocktailService.cocktailById(drinkId)
            Utils.logger("DrinkDetailsViewModel", "Response is: $response")
            if(response.drinks != null) {
                Utils.logger("DrinkDetailsViewModel", "Inside Response: ${response.drinks}")
                _drinkState.postValue(DrinkDetailsState.DrinkResponse(response.drinks[0]))
            }
            progress.postValue(false)
        }
    }

    fun cleanState() {
        _drinkState.value = DrinkDetailsState.Default
    }

}