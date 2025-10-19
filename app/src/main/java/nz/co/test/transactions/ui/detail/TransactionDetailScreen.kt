import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import nz.co.test.transactions.R
import nz.co.test.transactions.ui.detail.TransactionDetailViewModel
import nz.co.test.transactions.ui.home.UITransaction
import nz.co.test.transactions.ui.theme.AppTheme
import nz.co.test.transactions.ui.theme.AppTopBar
import nz.co.test.transactions.ui.theme.TransactionAppTheme

@Composable
fun TransactionDetailScreen(
    viewModel: TransactionDetailViewModel = hiltViewModel()
) {
    val state by viewModel.transactionState.collectAsState()

    TransactionDetail(state)
}

@Composable
fun TransactionDetail(transaction: UITransaction) {
    val contentPadding = 10.dp
    val smallPadding = 5.dp
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppTopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Text(
                text = transaction.amount,
                style = AppTheme.typography.xLargeText,
                modifier = Modifier.padding(end = smallPadding)
            )

            Text(
                text = transaction.description,
                style = AppTheme.typography.largeText,
                modifier = Modifier.padding(top = smallPadding)
            )

            Text(
                text = transaction.timeText,
                style = AppTheme.typography.largeText,
                modifier = Modifier.padding(top = smallPadding)
            )

            if (transaction.gstVisible) {
                Text(
                    text = stringResource(R.string.gst_s, transaction.gstText),
                    style = AppTheme.typography.largeText,
                    modifier = Modifier.padding(top = smallPadding)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionDetailScreenPreview() {
    TransactionAppTheme {
        TransactionDetail(
            UITransaction(
                "1",
                "Testing description",
                "$43.44",
                "04 Dec 2023 12:34 PM",
                "$54.33",
                true,
                R.color.green
            )
        )
    }
}