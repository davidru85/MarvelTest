package com.ruizurraca.detail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.detail.domain.models.CharactersData
import com.ruizurraca.detail.domain.repository.MarvelCharacterDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.ruizurraca.detail.domain.models.Result
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

    private val characterDetailLiveData = MutableLiveData<Result<CharactersData>>()

    val characterDetail: MutableLiveData<Result<CharactersData>>
        get() {
            return characterDetailLiveData
        }

    fun getCharacterDetail(charId: String? = null) {
        viewModelScope.launch {
            characterDetailRepository.getCharacterDetail(
                charId = savedStateHandle.get<String>(ID) ?: charId
            ).let {
                savedStateHandle.set(ID, charId)
                characterDetailLiveData.postValue(it)
            }
        }
    }
}