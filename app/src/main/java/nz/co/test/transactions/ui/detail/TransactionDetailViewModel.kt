package nz.co.test.transactions.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nz.co.test.transactions.domain.usecases.CalculateGSTAmountUseCase
import nz.co.test.transactions.domain.usecases.GetTransactionByIdUseCase
import nz.co.test.transactions.ui.home.UITransaction
import nz.co.test.transactions.ui.home.toUi
import nz.co.test.transactions.ui.utils.CurrencyFormatter
import nz.co.test.transactions.ui.utils.DateFormatter
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val calculateGSTAmountUseCase: CalculateGSTAmountUseCase,
    private val dateFormatter: DateFormatter,
    private val currencyFormatter: CurrencyFormatter,
) : ViewModel() {

    private val transactionId: String = savedStateHandle["transactionId"]
        ?: throw IllegalArgumentException("Transaction ID not found")

    private val _transactionState = MutableStateFlow<UITransaction>(UITransaction())
    val transactionState: StateFlow<UITransaction> = _transactionState.asStateFlow()

    init {
        viewModelScope.launch {
            getTransactionByIdUseCase(transactionId)?.let {
                _transactionState.value = it.toUi(
                    gst = calculateGSTAmountUseCase(BigDecimal(it.debit)),
                    dateFormatter = dateFormatter,
                    currencyFormatter = currencyFormatter
                )
            }
        }
    }

}