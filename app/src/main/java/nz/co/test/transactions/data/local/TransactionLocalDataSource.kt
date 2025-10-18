package nz.co.test.transactions.data.local

import kotlinx.coroutines.flow.Flow
import nz.co.test.transactions.data.local.entities.TransactionEntity

interface TransactionLocalDataSource {

    suspend fun saveTransactions(transactions: List<TransactionEntity>)

    suspend fun getAllTransactions(): Flow<List<TransactionEntity>>

    suspend fun getTransaction(id: Long): TransactionEntity?
}