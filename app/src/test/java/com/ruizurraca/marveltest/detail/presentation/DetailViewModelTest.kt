package com.ruizurraca.marveltest.detail.presentation

import androidx.lifecycle.SavedStateHandle
import com.ruizurraca.marveltest.detail.domain.repository.MarvelCharacterDetailRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @MockK
    var savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    @MockK
    var characterDetailRepository: MarvelCharacterDetailRepository = mockk(relaxed = true)

    lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = buildViewModel()
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "2", "3", "34"])
    fun `viewmodel getCharacterDetail should call repo getCharacterDetail`(charId: String) {
        every { savedStateHandle.get<String>(DetailViewModel.ID) } returns charId
        viewModel.getCharacterDetail(charId)

        coVerify { characterDetailRepository.getCharacterDetail(charId) }
    }

    private fun buildViewModel() = DetailViewModel(savedStateHandle, characterDetailRepository)
}