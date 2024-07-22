package com.sameer.dogbreeds.favouriteDogBreeds

import com.sameer.dogbreeds.data.repository.DataRepository
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.utils.BaseUnitTest
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test


@ExperimentalCoroutinesApi
class FavouriteDogBreedsRepositoryShould : BaseUnitTest() {
    private val dataRepository = mockk<DataRepository>()

    @Test
    fun `fetch dog breeds`() = runTest {
        // given
        val mockFavouriteDogBreeds = mockk<List<DogBreed>>(relaxed = true)

        every {
            dataRepository.getFavouriteDogBreeds()
        } returns flow { emit(mockFavouriteDogBreeds) }

        // when
        val getFavouriteDogBreeds = dataRepository.getFavouriteDogBreeds()

        //then
        getFavouriteDogBreeds.first() shouldBe mockFavouriteDogBreeds
    }

    @Test
    fun `add to favourites`() = runTest {
        // given
        val name = "african"
        val isFavourite = true

        coEvery {
            dataRepository.updateDogBreeds(name, isFavourite)
        } returns Unit

        // when
        val getFavouriteDogBreeds =
            dataRepository.updateDogBreeds(name, isFavourite)

        //then
        getFavouriteDogBreeds shouldBe Unit
    }
}