package nz.co.test.transactions.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nz.co.test.transactions.R
import nz.co.test.transactions.domain.usecases.CalculateGSTAmountUseCase
import nz.co.test.transactions.domain.usecases.GetTransactionsUseCase
import nz.co.test.transactions.ui.utils.CurrencyFormatter
import nz.co.test.transactions.ui.utils.DateFormatter
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val calculateGSTAmountUseCase: CalculateGSTAmountUseCase,
    private val dateFormatter: DateFormatter,
    private val currencyFormatter: CurrencyFormatter,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    init {
        fetchTransactions()
    }

    fun fetchTransactions() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val transactionResult = getTransactionsUseCase()
            if (transactionResult.isSuccess) {
                val transactions = transactionResult.getOrDefault(emptyList())
                    .sortedByDescending { it.transactionDate }
                    .map {
                        it.toUi(
                            gst = calculateGSTAmountUseCase(BigDecimal(it.debit)),
                            dateFormatter = dateFormatter,
                            currencyFormatter = currencyFormatter
                        )
                    }
                _uiState.value = _uiState.value.copy(transactions = transactions, isLoading = false)

            } else {
                _uiState.value.copy(isLoading = false, actionText = R.string.something_went_wrong)
            }
        }
    }

}