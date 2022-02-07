package com.ruizurraca.marveltest.presentation.home

import androidx.lifecycle.SavedStateHandle
import com.ruizurraca.marveltest.list.domain.repository.MarvelCharactersRepository
import com.ruizurraca.marveltest.list.presentation.home.HomeViewModel
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
class HomeViewModelTest {

    @MockK
    var savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    @MockK
    var charactersRepository: MarvelCharactersRepository = mockk(relaxed = true)

    lateinit var viewModel: HomeViewModel


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = buildViewModel()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4])
    fun `viewmodel getCharacters should call repo getcharacters`(offset: Int) {
        every { savedStateHandle.get<Int>(HomeViewModel.OFFSET) } returns offset
        every { savedStateHandle.get<String>(HomeViewModel.NAME) } returns null

        viewModel.getCharacters()

        coVerify { charactersRepository.getCharacters(offset, null) }
    }

    private fun buildViewModel() = HomeViewModel(savedStateHandle, charactersRepository)

}