package nz.co.test.transactions.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nz.co.test.transactions.R
import nz.co.test.transactions.ui.theme.AppTheme
import nz.co.test.transactions.ui.theme.AppTopBar
import nz.co.test.transactions.ui.theme.TransactionAppTheme

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onTransactionClick: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        AppTopBar()
        TransactionListUI(state, onTransactionClick)
    }
}

@Composable
fun TransactionListUI(state: HomeScreenState, onTransactionClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.transactions, key = { it.id }) { transaction ->
                TransactionItem(transaction, onClick = onTransactionClick)
            }
        }

        if (state.isLoading || state.transactions.isEmpty()) {

            Text(
                text = if (state.isLoading) "Loading Transactions..." else stringResource(state.actionText),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .wrapContentSize(Alignment.Center)
                    .padding(horizontal = 32.dp),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.largeText,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: UITransaction, onClick: (String) -> Unit) {

    val mediumPadding = 15.dp
    val smallPadding = 5.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(transaction.bgColor), shape = MaterialTheme.shapes.small)
            .clickable(onClick = {
                onClick(transaction.id)
            })
            .padding(mediumPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = transaction.amount,
                style = AppTheme.typography.xLargeText,
                modifier = Modifier.padding(end = smallPadding)
            )

            Text(
                text = transaction.description,
                style = AppTheme.typography.mediumText,
                modifier = Modifier.padding(top = smallPadding)
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = transaction.timeText,
                style = AppTheme.typography.smallText
            )

            if (transaction.gstVisible) {
                Text(
                    text = stringResource(R.string.gst_s, transaction.gstText),
                    style = AppTheme.typography.mediumText,
                    modifier = Modifier.padding(top = smallPadding)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionListPreview() {
    TransactionAppTheme {
        TransactionListUI(
            state = HomeScreenState(
                transactions =
                    listOf(
                        UITransaction(
                            id = "1",
                            description = "This is a test description",
                            amount = "$4,8475.85",
                            timeText = "27 Feb 2022, 12:00 PM",
                            gstText = "629.788",
                            gstVisible = true,
                            bgColor = R.color.green
                        ), UITransaction(
                            id = "",
                            description = "This is a test description",
                            amount = "-$4,8475.85",
                            timeText = "27 Feb 2022, 12:00 PM",
                            gstText = "629.788",
                            gstVisible = true,
                            bgColor = R.color.red
                        )
                    )
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {

    TransactionAppTheme {
        TransactionItem(
            UITransaction(
                id = "",
                description = "This is a test description",
                amount = "$4,8475.85",
                timeText = "27 Feb 2022, 12:00 PM",
                gstText = "629.788",
                gstVisible = true,
                bgColor = R.color.green
            )
        ) {}
    }
}