package com.example.truecaller.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.truecaller.R
import com.example.truecaller.databinding.FragmentBlankBinding
import com.example.truecaller.ui.viewmodel.MainViewModel
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
        binding.btnLoad.setOnClickListener { makeTheCalls() }
    }

    private fun makeTheCalls() {
        get10thChar()
        getEvery10thChar()
        getWordCounter()
    }

    private fun get10thChar() {
        mainViewModel.get10thChar().observe(viewLifecycleOwner) { responseBody ->
            responseBody?.let {
                val getBody = it.string()
                val body = getBody.split("<body>")[1].split("</body>")[0]

                binding.txt10Character.text = body[10].toString()
            }
        }
    }

    private fun getEvery10thChar() {
        mainViewModel.getEvery10thCharacter().observe(viewLifecycleOwner) { responseBody ->
            responseBody?.let {
                val getBody = it.string()
                val body = getBody.split("<body>")[1].split("</body>")[0]

                for (i in 10 until body.length step 10) {
                    val char = body[i]

                    binding.txtEvert10th.append("$i th char : $char \n")
                }
            }
        }
    }

    private fun getWordCounter() {
        mainViewModel.getWordCounter().observe(viewLifecycleOwner) { responseBody ->
            responseBody?.let {
                val getBody = it.string()
                val body = getBody.split("<body>")[1].split("</body>")[0]

                val words = body.split("\\s+".toRegex())

                val result = mutableMapOf<String, Int>()
                words.forEach { word ->
                    if (result.containsKey(word)) result[word] =
                        result[word]!! + 1 else result[word] = 1
                }

                binding.wordCounterText.text = result.entries.joinToString("\n")
            }
        }
    }
}