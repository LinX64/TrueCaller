package com.example.truecaller.data.api

import com.example.truecaller.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.mEndPoint)
    suspend fun getTrueCallerData(): ResponseBody
}