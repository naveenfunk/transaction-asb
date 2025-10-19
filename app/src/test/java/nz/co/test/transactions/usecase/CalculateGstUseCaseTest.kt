package nz.co.test.transactions.usecase

import junit.framework.TestCase.assertEquals
import nz.co.test.transactions.domain.usecases.CalculateGSTAmountUseCase
import org.junit.Test
import java.math.BigDecimal

class CalculateGstUseCaseTest {

    @Test
    fun `invoke should return calculated gst`() {
        val useCase = CalculateGSTAmountUseCase()
        val result = useCase(amount = BigDecimal("100"))

        val gstPercentage = useCase.GST_PERCENTAGE

        assertEquals(BigDecimal(gstPercentage), result)
    }
}