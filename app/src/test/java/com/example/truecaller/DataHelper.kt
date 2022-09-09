package com.example.truecaller

import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.jsoup.Jsoup

class DataHelper {

    companion object {
        fun getHtmlResponse(): ResponseBody {
            val document = Jsoup.parse("file.html", "UTF-8")
            return document.toString().toResponseBody()
        }

        fun getHtmlBody(mData: String): String {
            return mData.split("<body>")[1].split("</body>")[0]
        }
    }
}