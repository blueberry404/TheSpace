package com.blueberry.thespace

import android.util.Log
import java.text.SimpleDateFormat

fun String.toVerboseDateFormat(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    try {
        val date = sdf.parse(this)
        val df = SimpleDateFormat("dd MMM yyyy")
        return df.format(date)
    }
    catch(ex: Exception) {
        Log.e("TAG", ex.localizedMessage)
    }
    return this
}