package nz.co.test.transactions.ui.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateFormatter @Inject constructor() {

    fun format(dateString: String, inputFormat: String, outputFormat: String): String {
        return try {
            val inputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
            val outputFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())

            val date: Date? = inputFormatter.parse(dateString)

            if (date != null) {
                outputFormatter.format(date)
            } else {
                ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}