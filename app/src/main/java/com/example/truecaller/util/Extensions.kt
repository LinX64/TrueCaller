package com.example.truecaller.util

import android.view.View

fun getBodyUsingSplit(getBody: String) =
    getBody.split("<body>")[1].split("</body>")[0]

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}