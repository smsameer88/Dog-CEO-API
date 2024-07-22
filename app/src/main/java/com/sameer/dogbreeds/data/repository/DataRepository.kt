package com.sameer.dogbreeds.data.repository

import com.sameer.dogbreeds.data.Resource
import com.sameer.dogbreeds.data.local.LocalSource
import com.sameer.dogbreeds.data.remote.RemoteSource
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.domain.model.DogBreedImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteSource,
    private val localDataSource: LocalSource
) : DataSource {

    override fun getDogBreeds(): Flow<List<DogBreed>> {
        return localDataSource.getDogBreeds().map {
            if (it.isEmpty()) {
                remoteDataSource.getDogBreeds().data?.let { list ->
                    localDataSource.storeDogBreedListInDb(list)
                }
            }
            it
        }.catch {
            emitAll(flowOf(emptyList()))
        }
    }

    override suspend fun getDogBreedImages(breedName: String): Flow<Resource<List<String>>> {
        localDataSource.getDogBreedImages(breedName).data?.let {
            if (it.isNotEmpty()) {
                return flow { emit(localDataSource.getDogBreedImages(breedName)) }.flowOn(
                    Dispatchers.IO
                )
            }
        }
        remoteDataSource.getDogBreedImages(breedName).data?.let {
            localDataSource.storeDogBreedImageListInDb(DogBreedImages(it, breedName))
        }
        return flow { emit(remoteDataSource.getDogBreedImages(breedName)) }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateDogBreeds(name: String, isFavourite: Boolean) {
        localDataSource.updateDogBreeds(name, isFavourite)
    }

    override fun getFavouriteDogBreeds(): Flow<List<DogBreed>> {
        return localDataSource.getFavouriteDogBreeds()
    }
}
