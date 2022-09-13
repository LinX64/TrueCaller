package com.example.truecaller.data.repository

import com.example.truecaller.data.api.ApiService
import com.example.truecaller.util.Resource
import com.example.truecaller.util.ResponseHandler
import okhttp3.ResponseBody
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler
) {

    suspend fun getTrueCallerData(): Resource<ResponseBody> {
        return try {
            val mResponseBody = apiService.getTrueCallerData()
            responseHandler.handleSuccess(mResponseBody)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}