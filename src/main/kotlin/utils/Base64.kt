package de.fridolin1.utils

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.fromBase64(): String {
    return Base64.decode(this).toString(Charsets.UTF_8)
}

@OptIn(ExperimentalEncodingApi::class)
fun String.toBase64(): String {
    return Base64.decode(this).toString(Charsets.UTF_8)
}