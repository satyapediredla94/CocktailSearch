package com.example.cocktails.fragment.search

sealed class SearchState {

    object Default : SearchState()

    data class GetDrinkDetails(val drinkId: String) : SearchState()

}
