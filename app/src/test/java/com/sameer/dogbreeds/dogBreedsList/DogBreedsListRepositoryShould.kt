package com.sameer.dogbreeds.dogBreedsList

import com.sameer.dogbreeds.data.local.LocalDataSource
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.utils.BaseUnitTest
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DogBreedsListRepositoryShould : BaseUnitTest() {
    private val localDataSource: LocalDataSource = mockk()

    @Test
    fun `fetch dog breeds from local data source`() = runTest {
        // given
        val mockDogBreeds = mockk<List<DogBreed>>(relaxed = true)

        coEvery {
            localDataSource.getDogBreeds()
        } returns flow {
            emit(mockDogBreeds)
        }

        // when
        val useCaseValue =
            localDataSource.getDogBreeds()

        //then
        useCaseValue.first() shouldBe mockDogBreeds
    }
}