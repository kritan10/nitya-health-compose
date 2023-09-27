package com.kritan.nityahealth.base.extensions

fun String.shortenString(maxCharacters: Int = 13): String {
    if (this.length < maxCharacters) {
        return this
    }
    return this.substring(0, maxCharacters) + "..."
}