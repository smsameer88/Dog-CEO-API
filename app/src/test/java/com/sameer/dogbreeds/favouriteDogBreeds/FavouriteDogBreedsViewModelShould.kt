package com.sameer.dogbreeds.favouriteDogBreeds

import com.sameer.dogbreeds.data.repository.DataSource
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.ui.component.favouriteDogBreeds.FavouriteDogBreedsViewModel
import com.sameer.dogbreeds.utils.BaseUnitTest
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class FavouriteDogBreedsViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: FavouriteDogBreedsViewModel

    private val dataRepository: DataSource = mockk()

    @Test
    fun `initialize then fetch favourite dog breeds`() = runTest {
        // given
        val breed = DogBreed(
            name = "poodle",
            subBreeds = "",
            isFavourite = true,
            imageUrl = "dummy_url"
        )

        every { dataRepository.getFavouriteDogBreeds() } returns flow {
            emit(listOf(breed, breed))
        }

        // when
        // view model is initialized
        viewModel = FavouriteDogBreedsViewModel(dataRepository)

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 2
        viewModel.uiState.isLoading shouldBe false
    }

    @Test
    fun `initialize then fetch favourite dog breeds which has no items`() = runTest {
        // given
        every { dataRepository.getFavouriteDogBreeds() } returns flow {
            emit(emptyList())
        }

        // when
        // view model is initialized
        viewModel = FavouriteDogBreedsViewModel(dataRepository)

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 0
        viewModel.uiState.isLoading shouldBe false
    }
}