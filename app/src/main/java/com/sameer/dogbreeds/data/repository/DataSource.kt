package com.sameer.dogbreeds.data.repository

import com.sameer.dogbreeds.data.Resource
import com.sameer.dogbreeds.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getDogBreeds(): Flow<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Flow<Resource<List<String>>>
    suspend fun updateDogBreeds(name: String, isFavourite: Boolean)
    fun getFavouriteDogBreeds(): Flow<List<DogBreed>>
}
