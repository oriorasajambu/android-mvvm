package id.android.skeleton.common

import java.math.RoundingMode
import java.text.DecimalFormat

object Converter {
    fun String?.formatCurrency(
        fallbackStrategy: ((String?) -> String) = {
            it.takeIf { !it.isNullOrEmpty() } ?: "-"
        },
    ): String {
        if (this.isNullOrEmpty()) return fallbackStrategy.invoke(this)
        val number = this.toDoubleOrNull() ?: return fallbackStrategy.invoke(this)
        val formatter = DecimalFormat("#,###,###")
        formatter.roundingMode = RoundingMode.DOWN
        return formatter.format(number)
    }
}