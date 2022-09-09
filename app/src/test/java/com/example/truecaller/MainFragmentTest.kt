package com.example.truecaller

import androidx.test.runner.AndroidJUnit4
import com.example.truecaller.data.api.ApiService
import com.example.truecaller.data.repository.MainRepository
import com.example.truecaller.ui.viewmodel.MainViewModel
import com.example.truecaller.util.ResponseHandler
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {
    val mockApiService = mock<ApiService>()
    val mockHandler = mock<ResponseHandler>()

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var mainRepo: MainRepository

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainRepo = MainRepository(mockApiService, mockHandler)
        mainViewModel = MainViewModel(mainRepo)
    }

    @Test
    fun `GIVEN the html file THEN extract 10th character correctly`(): Unit = runBlocking {
        // given

        val mockResponse = DataHelper.getHtmlResponse()
        val mockApiService = mock<ApiService>()
        val repository = MainRepository(mockApiService, mockHandler)

        val mock = mockGet10Char(mockResponse)
        val getBody = repository.getTrueCaller10thCharacterRequest().data

        println(mock)


        //  whenever(mainRepo.getTrueCaller10thCharacterRequest()).thenReturn(mHtmlResponse)
    }

    private fun mockGet10Char(res: ResponseBody): ApiService =
        runBlocking {
            return@runBlocking mock<ApiService> {
                onBlocking {
                    getTrueCaller10thCharacterRequest()
                } doReturn res
            }
        }

}