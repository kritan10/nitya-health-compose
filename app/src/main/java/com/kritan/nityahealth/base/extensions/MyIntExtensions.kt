package com.kritan.nityahealth.base.extensions

fun Int?.blankWhenNull(): String {
    if (this == null) return ""
    return this.toString()
}