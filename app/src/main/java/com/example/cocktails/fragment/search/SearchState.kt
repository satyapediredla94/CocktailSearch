package com.example.cocktails.fragment.search

sealed class SearchState {

    object Default : SearchState()
    object HideKeyBoard : SearchState()
    data class GetDrinkDetails(val drinkId: String) : SearchState()
    object Error : SearchState()

}
