package nz.co.test.transactions.ui.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class CurrencyFormatter @Inject constructor(private val locale: Locale) {
    private val numberFormat = NumberFormat.getCurrencyInstance(locale)

    fun format(amount: BigDecimal): String = numberFormat.format(amount)
}