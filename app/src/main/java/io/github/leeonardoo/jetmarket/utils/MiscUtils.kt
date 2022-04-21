package io.github.leeonardoo.jetmarket.utils

import java.text.NumberFormat
import java.util.*

fun Double.toCurrencyFormat(): String {
    val fmt = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
    }

    return fmt.format(this)
}