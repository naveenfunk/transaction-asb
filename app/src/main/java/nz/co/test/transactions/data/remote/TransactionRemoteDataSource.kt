package nz.co.test.transactions.data.remote

import nz.co.test.transactions.data.model.Transaction

interface TransactionRemoteDataSource {

    suspend fun getTransactions() : Result<List<Transaction>>
}