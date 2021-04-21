package com.example.cocktails.data.drink_category

import androidx.room.Entity
import com.example.cocktails.utils.AppConstants

@Entity(tableName = AppConstants.DRINK_INGREDIENT_TABLE_NAME)
data class CategoryDrink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)