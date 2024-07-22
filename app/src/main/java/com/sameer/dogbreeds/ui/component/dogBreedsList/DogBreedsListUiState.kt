package com.sameer.dogbreeds.ui.component.dogBreedsList

import com.sameer.dogbreeds.domain.model.DogBreed

data class DogBreedsListUiState (
    var dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = true
)