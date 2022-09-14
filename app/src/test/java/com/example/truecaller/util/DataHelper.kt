package com.example.truecaller.util

fun getHtmlResponse() = ClassLoader.getSystemResource("file.html").readText()

fun getBodyUsingSplit(getBody: String) = getBody.split("<body>")[1].split("</body>")[0]
