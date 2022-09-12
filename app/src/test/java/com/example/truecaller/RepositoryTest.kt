package com.example.truecaller

import com.example.truecaller.data.api.ApiService
import com.example.truecaller.data.repository.MainRepository
import com.example.truecaller.util.DataHelper
import com.example.truecaller.util.ResponseHandler
import com.example.truecaller.util.getBodyUsingSplit
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RepositoryTest {
    private val mockHandler = mock<ResponseHandler>()

    @Test
    fun `GIVEN the html file THEN extract 10th character correctly`(): Unit = runBlocking {
        // given
        val mockResponse = DataHelper.getHtmlResponse().toResponseBody()
        val mockApiService = mockGet10Char(mockResponse)
        val mainRepository = MainRepository(mockApiService, mockHandler)

        val expectedChar = "p"

        // when
        val mRepo = mainRepository.getTrueCaller10thCharacterRequest().data?.string()
        val mBody = getBodyUsingSplit(mRepo!!)
        val actualChar = mBody[10].toString()

        // then
        assertEquals(expectedChar, actualChar)
    }

    @Test
    fun `WHEN the html file THEN return every 10th character`(): Unit = runBlocking {
        // given
        val mockResponse = DataHelper.getHtmlResponse().toResponseBody()
        val mockApiService = mockGet10Char(mockResponse)
        val mainRepository = MainRepository(mockApiService, mockHandler)

        val expected10thChar = "p"
        val expected20thChar = "e"

        // when
        val mRepo = mainRepository.getTrueCaller10thCharacterRequest().data?.string()
        val mBody = getBodyUsingSplit(mRepo!!)
        val actual10thChar = mBody[10].toString()
        val actual20thChar = mBody[20].toString()

        // then
        assertEquals(expected10thChar, actual10thChar)
        assertEquals(expected20thChar, actual20thChar)
    }

    @Test
    fun `WHEN the html file THEN return Word Counter string`(): Unit = runBlocking {
        // given
        val mockResponse = DataHelper.getHtmlResponse().toResponseBody()
        val mockApiService = mockGet10Char(mockResponse)
        val mainRepository = MainRepository(mockApiService, mockHandler)

        val expectedChars = "".toResponseBody()

        // when
        val mRepo = mainRepository.getTrueCallerWordCounterRequest().data

        // then
        whenever(mRepo).thenReturn(expectedChars)
    }

    @Test
    fun `WHEN the html file is empty THEN return empty string`(): Unit = runBlocking {
        // given
        val mockResponse = DataHelper.getHtmlResponse().toResponseBody()
        val mockApiService = mockGet10Char(mockResponse)
        val mainRepository = MainRepository(mockApiService, mockHandler)

        val expectedChar = "".toResponseBody()

        // when
        val mRepo = mainRepository.getTrueCaller10thCharacterRequest().data

        // then
        whenever(mRepo).thenReturn(expectedChar)
    }

    private fun mockGet10Char(res: ResponseBody): ApiService =
        runBlocking {
            return@runBlocking mock<ApiService> {
                onBlocking {
                    getTrueCaller10thCharacterRequest()
                } doReturn res
            }
        }

    private fun mockGetEvery10Char(res: ResponseBody): ApiService =
        runBlocking {
            return@runBlocking mock<ApiService> {
                onBlocking {
                    getTrueCallerEvery10thCharacterRequest()
                } doReturn res
            }
        }

    private fun mockGetWordCounter(res: ResponseBody): ApiService =
        runBlocking {
            return@runBlocking mock<ApiService> {
                onBlocking {
                    getTrueCallerWordCounterRequest()
                } doReturn res
            }
        }
}