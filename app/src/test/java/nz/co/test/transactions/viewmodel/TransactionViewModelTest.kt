package nz.co.test.transactions.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nz.co.test.transactions.MainCoroutineRule
import nz.co.test.transactions.domain.usecases.CalculateGSTAmountUseCase
import nz.co.test.transactions.domain.usecases.GetTransactionsUseCase
import nz.co.test.transactions.testdata.testTransactions
import nz.co.test.transactions.ui.home.HomeScreenViewModel
import nz.co.test.transactions.ui.home.toUi
import nz.co.test.transactions.ui.utils.CurrencyFormatter
import nz.co.test.transactions.ui.utils.DateFormatter
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
class TransactionViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(StandardTestDispatcher())

    private lateinit var viewModel: HomeScreenViewModel

    @MockK
    lateinit var getTransactionsUseCase: GetTransactionsUseCase
    private lateinit var calculateGSTAmountUseCase: CalculateGSTAmountUseCase

    val locale = Locale("en", "US")

    private val currencyFormatter = CurrencyFormatter(locale)
    private val dateFormatter: DateFormatter = DateFormatter()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        calculateGSTAmountUseCase = CalculateGSTAmountUseCase()
    }

    @Test
    fun `when fetchTransactions called then transactions are emitted`() = runTest {
        coEvery { getTransactionsUseCase() } returns Result.success(testTransactions)
        viewModel = HomeScreenViewModel(
            getTransactionsUseCase = getTransactionsUseCase,
            calculateGSTAmountUseCase = calculateGSTAmountUseCase,
            dateFormatter = dateFormatter,
            currencyFormatter = currencyFormatter
        )
        val expectedTransactions = testTransactions
            .sortedByDescending { it.transactionDate }
            .map {
                it.toUi(
                    gst = calculateGSTAmountUseCase(BigDecimal(it.debit)),
                    dateFormatter = dateFormatter,
                    currencyFormatter
                )
            }

        advanceUntilIdle()

        coVerify(exactly = 1) { getTransactionsUseCase() }

        val finalState = viewModel.uiState.value
        Assert.assertEquals(false, finalState.isLoading)
        Assert.assertEquals(expectedTransactions, finalState.transactions)
    }

}