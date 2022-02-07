package com.ruizurraca.marveltest.list.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ruizurraca.marveltest.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }
}