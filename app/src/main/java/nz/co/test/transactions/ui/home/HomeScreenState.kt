package nz.co.test.transactions.ui.home

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import nz.co.test.transactions.R
import nz.co.test.transactions.data.local.entities.TransactionEntity
import nz.co.test.transactions.ui.utils.CurrencyFormatter
import nz.co.test.transactions.ui.utils.DateFormatter
import nz.co.test.transactions.ui.utils.INPUT_DATE_FORMATTER
import nz.co.test.transactions.ui.utils.OUTPUT_DATE_FORMATTER
import java.math.BigDecimal

data class HomeScreenState(
    val transactions: List<UITransaction> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes val actionText: Int = R.string.no_transactions_found
)

data class UITransaction(
    val id: String = "",
    val description: String = "",
    val amount: String = "",
    val timeText: String = "",
    val gstText: String = "",
    val gstVisible: Boolean = false,
    @ColorRes val bgColor: Int = R.color.white
)

fun TransactionEntity.toUi(
    gst: BigDecimal,
    dateFormatter: DateFormatter,
    currencyFormatter: CurrencyFormatter
): UITransaction {
    val showGst: Boolean
    val bgColor: Int
    val amount: BigDecimal
    val creditAmt = BigDecimal(credit)
    val debitAmt = BigDecimal(debit)
    if (creditAmt == BigDecimal(0)) {
        showGst = true
        bgColor = R.color.red
        amount = -debitAmt
    } else {
        showGst = false
        bgColor = R.color.green
        amount = creditAmt
    }
    return UITransaction(
        id = id.toString(),
        timeText = dateFormatter.format(
            transactionDate,
            INPUT_DATE_FORMATTER,
            OUTPUT_DATE_FORMATTER
        ),
        description = summary,
        amount = currencyFormatter.format(amount),
        gstText = currencyFormatter.format(gst),
        gstVisible = showGst,
        bgColor = bgColor
    )
}