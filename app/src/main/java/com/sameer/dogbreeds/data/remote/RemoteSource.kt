package com.sameer.dogbreeds.data.remote

import com.sameer.dogbreeds.data.Resource
import com.sameer.dogbreeds.domain.model.DogBreed

interface RemoteSource {
    suspend fun getDogBreeds(): Resource<List<DogBreed>>
    suspend fun getDogBreedImages(breedName: String): Resource<List<String>>
}

