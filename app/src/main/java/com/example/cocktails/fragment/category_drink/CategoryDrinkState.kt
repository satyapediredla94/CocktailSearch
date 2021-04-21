package com.example.cocktails.fragment.category_drink

import com.example.cocktails.data.drink_category.CategoryDrink

sealed class CategoryDrinkState {

    object Default : CategoryDrinkState()

    data class NavigateToDrinkDetails(val drinkId: String) : CategoryDrinkState()

}
