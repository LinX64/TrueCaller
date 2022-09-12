package com.example.truecaller.ui.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.truecaller.R
import com.example.truecaller.databinding.FragmentBlankBinding
import com.example.truecaller.ui.viewmodel.MainViewModel
import com.example.truecaller.util.getBodyUsingSplit
import com.example.truecaller.util.inVisible
import com.example.truecaller.util.visible
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
        binding.btnLoad.setOnClickListener { showLoaderForASecond() }
    }

    private fun showLoaderForASecond() {
        binding.progressBar.visible()

        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBar.inVisible()

            makeTheCalls()
        }, 1000)
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
                val body = getBodyUsingSplit(getBody)

                binding.txt10Character.text = body[10].toString()
            }
        }
    }

    private fun getEvery10thChar() {
        mainViewModel.getEvery10thCharacter().observe(viewLifecycleOwner) { responseBody ->
            responseBody?.let {
                val getBody = it.string()
                val body = getBodyUsingSplit(getBody)

                for (i in 10 until body.length step 10) {
                    val char = body[i]

                    binding.txtEvery10th.append("$i th char : $char \n")
                }
            }
        }
    }

    private fun getWordCounter() {
        mainViewModel.getWordCounter().observe(viewLifecycleOwner) { responseBody ->
            responseBody?.let {
                val getBody = it.string()
                val body = getBodyUsingSplit(getBody)

                val words = body.split("\\s+".toRegex())

                val result = mutableMapOf<String, Int>()
                words.forEach { word ->
                    if (result.containsKey(word)) result[word] =
                        result[word]!! + 1 else result[word] = 1
                }

                binding.wordCounterText.text = "Word Counter : " + result.entries.joinToString("\n")
            }
        }
    }
}