package com.example.cocktails.fragment.drink

sealed class DrinkState {

    data class NavigateToCategory(val category: String) : DrinkState()

    object Default : DrinkState()

}
