package nz.co.test.transactions.data.local

import javax.inject.Inject
import nz.co.test.transactions.data.local.entities.TransactionEntity

class TransactionLocalDataSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionLocalDataSource {

    override suspend fun saveTransactions(transactions: List<TransactionEntity>) {
        transactionDao.clearAll()
        transactionDao.insertAll(transactions)
    }

    override suspend fun getTransaction(id: Long): TransactionEntity? {
        return transactionDao.getTransactionById(id)
    }
}