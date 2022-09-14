package com.example.truecaller

import com.example.truecaller.data.api.ApiService
import com.example.truecaller.data.repository.MainRepository
import com.example.truecaller.util.ResponseHandler
import com.example.truecaller.util.getBodyUsingSplit
import com.example.truecaller.util.getHtmlResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class RepositoryTest {

    private val mockHandler = mock<ResponseHandler>()
    private lateinit var mainRepository: MainRepository
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = mockApiService()
        mainRepository = MainRepository(apiService, mockHandler)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN the html file THEN extract 10th character correctly`() = runTest {
        // given
        val mRepository = mainRepository.getTrueCallerData()

        // when
        val expectedChar = "p"
        val getBody = mRepository.data?.string()?.let { getBodyUsingSplit(it) }?.trim()
        val actualChar = getBody?.get(10).toString()

        // then
        assertEquals(expectedChar, actualChar)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN the html file THEN return every 10th character`() = runTest {
        // given
        val mRepository = mainRepository.getTrueCallerData()

        // when
        val expected10thChar = "p"
        val expected20thChar = "e"

        val getBody = mRepository.data?.string()?.let { getBodyUsingSplit(it) }?.trim()
        val actual10thChar = getBody?.get(10).toString()
        val actual20Char = getBody?.get(20).toString()

        // then
        assertEquals(expected10thChar, actual10thChar)
        assertEquals(expected20thChar, actual20Char)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN the html file THEN return Word Counter string`() = runTest {
        // given
        val mRepository = mainRepository.getTrueCallerData()

        // when
        val getBody = mRepository.data?.string()?.let { getBodyUsingSplit(it) }?.trim()
        val words = getBody?.split("\\s+".toRegex())
        val expectedFirstChar = "!<md:absolute"

        // then
        val result = mutableMapOf<String, Int>().toSortedMap()
        words?.forEach { word ->
            if (result.containsKey(word))
                result[word] = result[word]!! + 1
            else result[word] = 1
        }

        val getFirstItem = result.firstKey()
        assertEquals(expectedFirstChar, getFirstItem)
    }

    private fun mockApiService(): ApiService =
        runBlocking {
            return@runBlocking mock<ApiService> {
                onBlocking {
                    getTrueCallerData()
                } doReturn getHtmlResponse().toResponseBody()
            }
        }
}