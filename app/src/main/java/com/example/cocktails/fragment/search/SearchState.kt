package com.example.cocktails.fragment.search

import com.example.cocktails.data.Drink

sealed class SearchState {

    object Default : SearchState()

    data class GetDrinkDetails(val drinkId: String) : SearchState()

}
