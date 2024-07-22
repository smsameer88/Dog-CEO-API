package com.sameer.dogbreeds.ui.component.favouriteDogBreeds

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameer.dogbreeds.data.repository.DataSource
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.utils.extensions.capitalizeFirstLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteDogBreedsViewModel @Inject constructor(
    private val dataRepository: DataSource
) : ViewModel() {
    var uiState by mutableStateOf(FavouriteUiState())
        private set

    init {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dataRepository.getFavouriteDogBreeds().collect { result ->
                uiState =
                    uiState.copy(
                        isLoading = false,
                        dogBreeds = result.map {
                            DogBreed(
                                name = it.name.capitalizeFirstLetter(),
                                subBreeds = it.subBreeds,
                                imageUrl = it.imageUrl,
                                isFavourite = it.isFavourite
                            )
                        })
            }
        }
    }
}