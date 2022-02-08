package com.ruizurraca.marveltest.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ruizurraca.marveltest.databinding.FragmentDetailBinding
import com.ruizurraca.marveltest.detail.domain.models.CharactersData
import com.ruizurraca.marveltest.detail.domain.models.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    private var charId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        charId = args.charId
        return binding.root
    }

    private fun setLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        requestCharacterDetail()
    }

    private fun requestCharacterDetail() {
        setLoading(true)
        viewModel.getCharacterDetail("$charId")
    }

    private fun initObservers() {
        viewModel.characterDetail.observe(viewLifecycleOwner, { characters ->
            when (characters) {
                is Result.Success -> {
                    setLoading(false)
                    CharactersData.toView(characters.data).results?.first()?.let { characterView ->
                        binding.characterDetailView.setValue(characterView)
                    }
                }
                is Result.InProgress -> {
                    setLoading(true)
                }
                else -> {
                    setLoading(false)
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        binding.characterDetailView.clearLinks()
    }

    fun initBindings() {}
}