package com.sameer.dogbreeds.ui.component.dogBreedsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameer.dogbreeds.data.repository.DataRepository
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.utils.extensions.capitalizeFirstLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedsListViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    var uiState by mutableStateOf(DogBreedsListUiState())
        private set

    init {
        getDogBreedsList()
    }

    private fun getDogBreedsList() {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dataRepository.getDogBreeds().collect { result ->
                uiState = if (result.isNotEmpty()) {
                    uiState.copy(
                        isLoading = false,
                        dogBreeds = result.map { // Returns a list containing the results of applying
                            // the given transform function to each element in the original collection.
                            DogBreed(
                                name = it.name.capitalizeFirstLetter(),
                                subBreeds = it.subBreeds,
                                imageUrl = it.imageUrl,
                                isFavourite = it.isFavourite
                            )
                        })
                } else {
                    uiState.copy(isLoading = false, dogBreeds = result)
                }
            }
        }
    }
}