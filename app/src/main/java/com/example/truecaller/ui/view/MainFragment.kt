package com.example.truecaller.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.truecaller.R
import com.example.truecaller.databinding.FragmentBlankBinding
import com.example.truecaller.ui.viewmodel.MainViewModel
import com.example.truecaller.util.UiState
import com.example.truecaller.util.inVisible
import com.example.truecaller.util.visible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_blank) {

    private lateinit var binding: FragmentBlankBinding
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        binding.btnLoad.setOnClickListener { observeTheCalls() }
    }

    private fun observeTheCalls() {
        observe10thChar()
        observeEvery10thChar()
        observeWordCounter()
    }

    private fun observe10thChar() {
        mainViewModel.get10thChar()
            .observe(viewLifecycleOwner) { uiState ->

                when (uiState) {
                    is UiState.Loading -> binding.progressBar.visible()
                    is UiState.Success -> {
                        binding.progressBar.inVisible()
                        binding.txt10Character.text = uiState.data.toString()
                    }
                    is UiState.Error -> {
                        binding.progressBar.inVisible()
                        showErrorDialog(uiState.message)
                    }
                }
            }
    }

    private fun observeEvery10thChar() {
        mainViewModel.getEvery10thCharacter()
            .observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is UiState.Loading -> binding.progressBar.visible()

                    is UiState.Success -> {
                        binding.progressBar.inVisible()
                        binding.txtEvery10th.text = uiState.data
                    }
                    is UiState.Error -> {
                        binding.progressBar.inVisible()
                        showErrorDialog(uiState.message)
                    }
                }
            }
    }

    private fun observeWordCounter() {
        mainViewModel.getWordCounter()
            .observe(viewLifecycleOwner) { uiState ->

                when (uiState) {
                    is UiState.Loading -> binding.progressBar.visible()
                    is UiState.Success -> {
                        binding.progressBar.inVisible()
                        binding.wordCounterText.text = uiState.data
                    }
                    is UiState.Error -> {
                        binding.progressBar.inVisible()
                        showErrorDialog(uiState.message)
                    }
                }
            }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}