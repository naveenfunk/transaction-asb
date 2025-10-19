package nz.co.test.transactions.testdata

import nz.co.test.transactions.data.local.entities.TransactionEntity

val testTransactions = listOf(
    TransactionEntity(
        2,
        transactionDate = "2021-08-31T12:47:10",
        summary = "summary_test_2",
        credit = "0",
        debit = "20"
    ),
    TransactionEntity(
        1,
        transactionDate = "2021-08-31T15:47:10",
        summary = "summary_test",
        credit = "10",
        debit = "0"
    ),
)