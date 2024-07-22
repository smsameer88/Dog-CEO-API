package com.sameer.dogbreeds.data.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.domain.model.DogBreedImages
import com.sameer.dogbreeds.utils.Converters

@Database(version = 1, entities = [DogBreed::class, DogBreedImages::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class DogBreedsDatabase : RoomDatabase() {

    abstract fun dogBreedsDao(): DogBreedsDao
}