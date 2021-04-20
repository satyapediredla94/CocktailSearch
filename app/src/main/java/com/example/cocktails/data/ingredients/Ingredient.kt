package com.example.cocktails.data.ingredients

import androidx.room.Entity
import com.example.cocktails.utils.AppConstants

@Entity(tableName = AppConstants.INGREDITENT_LIST_TABLE)
data class Ingredient(
    val strIngredient1: String
)