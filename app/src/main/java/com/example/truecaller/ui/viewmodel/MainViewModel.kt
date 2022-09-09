package com.example.truecaller.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.truecaller.data.repository.MainRepository
import com.example.truecaller.di.IoDispatcher
import com.example.truecaller.util.Resource
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
        val deferredOne = getTrueCaller10thCharacterRequest().data
        emit(deferredOne)
    }

    fun getEvery10thCharacter() = liveData(ioDispatcher) {
        val deferredTwo = getTrueCallerEvery10thCharacterRequest().data
        emit(deferredTwo)
    }

    fun getWordCounter() = liveData(ioDispatcher) {
        val deferredThree = getTrueCallerWordCounterRequest().data
        emit(deferredThree)
    }

    /*
     * These are the methods that will be called from the viewModel to get the data from the repository.
     */
    private suspend fun getTrueCaller10thCharacterRequest(): Resource<ResponseBody> =
        viewModelScope.async {
            return@async mainRepository.getTrueCaller10thCharacterRequest()
        }.await()

    private suspend fun getTrueCallerEvery10thCharacterRequest(): Resource<ResponseBody> =
        viewModelScope.async {
            return@async mainRepository.getTrueCallerEvery10thCharacterRequest()
        }.await()

    private suspend fun getTrueCallerWordCounterRequest(): Resource<ResponseBody> =
        viewModelScope.async {
            return@async mainRepository.getTrueCallerWordCounterRequest()
        }.await()
}