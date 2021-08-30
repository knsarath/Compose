package com.app.compose.ui

/**
 * Created by Sarath Kn on 30/08/21.
 */

fun String.firstWord(): String {
    val split = this.split(" ")
    return split.first()
}

fun String.exceptFirstWord(): String {
    val split = this.split(" ", limit = 2)
    return if (split.size > 1) {
        split[1]
    } else {
        this
    }
}