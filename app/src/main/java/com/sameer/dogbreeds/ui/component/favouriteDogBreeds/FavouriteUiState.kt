package com.sameer.dogbreeds.ui.component.favouriteDogBreeds

import com.sameer.dogbreeds.domain.model.DogBreed

data class FavouriteUiState (
    val dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = false,
)