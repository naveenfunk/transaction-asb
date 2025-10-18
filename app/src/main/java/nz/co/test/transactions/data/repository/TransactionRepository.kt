package nz.co.test.transactions.data.repository

import nz.co.test.transactions.data.local.entities.TransactionEntity

interface TransactionRepository {

    suspend fun getTransactions(): Result<List<TransactionEntity>>
    suspend fun getTransactionById(id: Long): TransactionEntity?
}