package com.example.cocktails.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cocktails.utils.AppConstants

@Entity(tableName = AppConstants.INGREDIENT_TABLE_NAME)
data class Ingredient(
    @PrimaryKey
    val idIngredient: String,
    val strABV: String,
    val strAlcohol: String,
    val strDescription: String,
    val strIngredient: String,
    val strType: String
)