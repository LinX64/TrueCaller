package com.example.truecaller.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.truecaller.data.repository.MainRepository
import com.example.truecaller.di.IoDispatcher
import com.example.truecaller.util.Resource
import com.example.truecaller.util.Status.ERROR
import com.example.truecaller.util.Status.SUCCESS
import com.example.truecaller.util.UiState
import com.example.truecaller.util.getBodyUsingSplit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun get10thChar() = liveData(ioDispatcher) {
        emit(UiState.Loading)

        val response = loadDataRequest()
        when (response.status) {
            SUCCESS -> {
                val body = response.data?.string()?.let { getBodyUsingSplit(it) }
                val m10thChar = body?.get(10)
                emit(UiState.Success(m10thChar))
            }
            ERROR -> emit(UiState.Error(response.message ?: "Error Occurred!"))

            else -> emit(UiState.Loading)
        }
    }

    fun getEvery10thCharacter() = liveData(ioDispatcher) {
        val response = loadDataRequest()
        when (response.status) {
            SUCCESS -> {
                response.data?.string()?.let {
                    val body = getBodyUsingSplit(it)

                    val result = StringBuilder()
                    for (i in 10 until body.length step 10) {
                        result.append("$i th char : ${body[i]} \n")
                    }

                    emit(UiState.Success(result))
                }

            }
            ERROR -> emit(UiState.Error(response.message ?: "Error Occurred!"))

            else -> emit(UiState.Loading)
        }
    }

    fun getWordCounter() = liveData(ioDispatcher) {
        val response = loadDataRequest()

        when (response.status) {
            SUCCESS -> {
                response.data?.string()?.let {
                    val body = getBodyUsingSplit(it)
                    val words = body.split("\\s+".toRegex())

                    val result = mutableMapOf<String, Int>()
                    words.forEach { word ->
                        if (result.containsKey(word)) result[word] =
                            result[word]!! + 1 else result[word] = 1
                    }

                    emit(UiState.Success(result.entries.joinToString("\n")))
                }

            }
            ERROR -> emit(UiState.Error(response.message ?: "Error Occurred!"))

            else -> emit(UiState.Loading)
        }
    }

    private suspend fun loadDataRequest(): Resource<ResponseBody> =
        viewModelScope.async {
            return@async mainRepository.getTrueCallerData()
        }.await()
}