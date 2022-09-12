package com.example.truecaller.util

class DataHelper {

    companion object {
        fun getHtmlResponse(): String {
            return ClassLoader.getSystemResource("file.html").readText()
        }

        fun getHtmlBody(mData: String): String {
            return mData.split("<body>")[1].split("</body>")[0]
        }

        fun getBodyUsingSplit(getBody: String) =
            getBody.split("<body>")[1].split("</body>")[0]
    }
}