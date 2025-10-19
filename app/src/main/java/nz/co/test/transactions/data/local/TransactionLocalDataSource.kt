package nz.co.test.transactions.data.local

import nz.co.test.transactions.data.local.entities.TransactionEntity

interface TransactionLocalDataSource {

    suspend fun saveTransactions(transactions: List<TransactionEntity>)

    suspend fun getTransaction(id: Long): TransactionEntity?
}