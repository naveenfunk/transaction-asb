package nz.co.test.transactions.data.remote

import nz.co.test.transactions.data.model.Transaction
import javax.inject.Inject

class TransactionRemoteDataSourceImpl @Inject constructor(
    private val transactionsService: TransactionsService,
) : TransactionRemoteDataSource {

    override suspend fun getTransactions(): Result<List<Transaction>> {
        return try {
            val response = transactionsService.retrieveTransactions()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}