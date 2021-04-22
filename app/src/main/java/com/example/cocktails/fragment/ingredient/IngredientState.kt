package com.example.cocktails.fragment.ingredient

sealed class IngredientState {

    data class NavigateToIngredientDetails(val ingredient: String) : IngredientState()

    object Default: IngredientState()

    object Error : IngredientState()

}
