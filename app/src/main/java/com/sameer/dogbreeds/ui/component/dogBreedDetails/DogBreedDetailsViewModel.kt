package com.sameer.dogbreeds.ui.component.dogBreedDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameer.dogbreeds.data.Resource
import com.sameer.dogbreeds.data.repository.DataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataRepository: DataSource
) : ViewModel() {

    private val nameType = checkNotNull(savedStateHandle["name"]).toString()

    var uiState by mutableStateOf(DogBreedDetailsUiState())
        private set

    init {
        getDogBreedImages(nameType.lowercase())
    }

    fun getDogBreedImages(name: String) {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            dataRepository.getDogBreedImages(name).collect { result ->
                handleDogBreedImagesResponse(result)
            }
        }
    }

    private fun handleDogBreedImagesResponse(result: Resource<List<String>>) {
        uiState = when (result) {
            is Resource.Success -> {
                uiState.copy(
                    isLoading = false,
                    dogBreedsImages = result.data
                )
            }
            is Resource.DataError -> {
                result.errorCode
                uiState.copy(isLoading = false, dogBreedsImages = emptyList())
            }
            is Resource.Loading -> {
                uiState.copy(isLoading = true)
            }
        }
    }

    fun addToFavourites(dogName: String, isFavourite: Boolean) {
        viewModelScope.launch {
            dataRepository.updateDogBreeds(name = dogName, isFavourite = isFavourite)
        }
    }
}