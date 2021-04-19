package com.example.cocktails.fragment.drink_details

import com.example.cocktails.data.Drink

sealed class DrinkDetailsState {

    object Default : DrinkDetailsState()

    data class DrinkResponse(val drink: Drink) : DrinkDetailsState()

}
