package com.sameer.dogbreeds.dogBreedsList

import com.sameer.dogbreeds.data.repository.DataRepository
import com.sameer.dogbreeds.domain.model.DogBreed
import com.sameer.dogbreeds.ui.component.dogBreedsList.DogBreedsListViewModel
import com.sameer.dogbreeds.utils.BaseUnitTest
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test


@ExperimentalCoroutinesApi
class DogBreedsListViewModelShould : BaseUnitTest(){

    private lateinit var viewModel: DogBreedsListViewModel

    private val dataRepository: DataRepository = mockk()

    @Test
    fun `get dog list from repository`() = runTest {

        viewModel =  mockSuccessfulCase()

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 2
        viewModel.uiState.isLoading shouldBe false
    }

    @Test
    fun `emit empty list when receive error`() = runTest {

        viewModel =  mockErrorCase()

        // then
        viewModel.uiState.dogBreeds?.size shouldBe 0
        viewModel.uiState.isLoading shouldBe false
    }

    private fun mockErrorCase() : DogBreedsListViewModel {
        every { dataRepository. getDogBreeds() } returns flow {
            emit(emptyList())
        }

        return  DogBreedsListViewModel(dataRepository)
    }

    private fun mockSuccessfulCase() : DogBreedsListViewModel {
        val breed = DogBreed(
            name = "poodle",
            subBreeds = "",
            isFavourite = false,
            imageUrl = "dummy_url"
        )

        every { dataRepository.getDogBreeds() } returns flow {
            emit(listOf(breed, breed))
        }

        return DogBreedsListViewModel(dataRepository)
    }
}