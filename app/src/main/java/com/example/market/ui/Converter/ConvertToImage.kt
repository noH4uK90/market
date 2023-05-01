package com.example.market.ui.Converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Base64

fun String.toBitmap(): Bitmap {
    var decodedBytes = Base64.getDecoder().decode(this)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}