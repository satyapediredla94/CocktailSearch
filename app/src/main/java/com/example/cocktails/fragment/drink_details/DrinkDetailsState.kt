package com.example.cocktails.fragment.drink_details

import com.example.cocktails.data.search.Drink

sealed class DrinkDetailsState {

    object Default : DrinkDetailsState()

    data class DrinkResponse(val drink: Drink) : DrinkDetailsState()

    object Error : DrinkDetailsState()

}
