package com.example.foodappcompose.data

import androidx.annotation.DrawableRes

data class CategoryGrid(
    val title: String,
    val description: String,
    val offer: String,
    val duration: String,
    @DrawableRes val iconId: Int,
)
