package com.ruizurraca.marveltest.detail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.marveltest.detail.domain.repository.MarvelCharacterDetailRepository
import com.ruizurraca.marveltest.detail.domain.models.CharactersData
import com.ruizurraca.marveltest.detail.domain.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val characterDetailRepository: MarvelCharacterDetailRepository
) :
    ViewModel() {

    companion object {
        val ID = "id_key"
    }

    private val charactersLiveData = MutableLiveData<Result<CharactersData>>()

    val characters: MutableLiveData<Result<CharactersData>>
        get() {
            return charactersLiveData
        }

    fun getCharacterDetail(charId: String? = null) {
        viewModelScope.launch {
            characterDetailRepository.getCharacterDetail(
                charId = savedStateHandle.get<String>(ID) ?: charId
            ).let {
                savedStateHandle.set(ID, charId)
                charactersLiveData.postValue(it)
            }
        }
    }
}