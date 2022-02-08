package com.ruizurraca.marveltest.list.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ruizurraca.marveltest.databinding.FragmentHomeBinding
import com.ruizurraca.marveltest.list.domain.models.CharactersData
import com.ruizurraca.marveltest.list.domain.models.Result
import com.ruizurraca.marveltest.list.presentation.home.models.CharactersView
import com.ruizurraca.marveltest.utils.EndlessRecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    private val charactersAdapter = CharactersAdapter().apply {
        listener = object : CharacterClickListener {
            override fun characterClick(character: CharactersView.CharacterView) {
                viewCharacterDetail(character)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
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
        requestCharacters()
    }

    private fun requestCharacters(offset: Int? = null) {
        setLoading(true)
        viewModel.getCharacters(offset)
    }

    private fun initObservers() {
        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            when (characters) {
                is Result.Success -> {
                    setLoading(false)
                    charactersAdapter.fillData(CharactersData.toView(characters.data))
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

    private fun initBindings() {
        binding.recyclerView.apply {
            val lManager = StaggeredGridLayoutManager(
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            layoutManager = lManager
            adapter = charactersAdapter
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(lManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    requestCharacters(totalItemsCount)
                }
            })
        }
    }

    private fun viewCharacterDetail(character: CharactersView.CharacterView) {
        character.id?.let {
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailActivity(it)
            findNavController().navigate(directions)
        }
    }
}