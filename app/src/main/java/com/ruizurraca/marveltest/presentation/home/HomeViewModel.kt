package com.ruizurraca.marveltest.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.marveltest.domain.models.CharactersData
import com.ruizurraca.marveltest.domain.models.Result
import com.ruizurraca.marveltest.domain.repository.MarvelCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val charactersRepository: MarvelCharactersRepository
) :
    ViewModel() {

    companion object {
        private val OFFSET = "OFFSET"
        private val NAME = "NAME"
    }

    private val charactersLiveData = MutableLiveData<Result<CharactersData>>()

    val characters: MutableLiveData<Result<CharactersData>>
        get() {
            return charactersLiveData
        }

    fun getCharacters(offset: Int? = null, name: String? = null, limit: Int? = null) {
        viewModelScope.launch {
            savedStateHandle.get<String>(NAME)
            savedStateHandle.get<Int>(OFFSET)
            charactersRepository.getCharacters(
                offset = savedStateHandle.get<Int>(OFFSET) ?: offset,
                name = savedStateHandle.get<String>(NAME) ?: name,
                limit = limit
            ).let {
                savedStateHandle.set(OFFSET, offset)
                savedStateHandle.set(NAME, name)
                charactersLiveData.postValue(it)
            }
        }
    }
}