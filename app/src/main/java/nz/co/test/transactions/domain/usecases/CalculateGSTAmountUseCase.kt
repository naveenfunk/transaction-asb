package nz.co.test.transactions.domain.usecases

import java.math.BigDecimal
import javax.inject.Inject

class CalculateGSTAmountUseCase @Inject constructor() {

    val GST_PERCENTAGE = 15

    operator fun invoke(amount: BigDecimal): BigDecimal {
        return amount.multiply(BigDecimal(GST_PERCENTAGE)).divide(BigDecimal(100))
    }
}