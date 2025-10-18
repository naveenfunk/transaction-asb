package nz.co.test.transactions.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import nz.co.test.transactions.data.local.TransactionLocalDataSource
import nz.co.test.transactions.data.local.entities.TransactionEntity
import nz.co.test.transactions.data.model.toEntityList
import nz.co.test.transactions.data.remote.TransactionRemoteDataSource
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: TransactionRemoteDataSource,
    private val localDataSource: TransactionLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : TransactionRepository {

    override suspend fun getTransactions(): Result<List<TransactionEntity>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val remoteTransactions =
                    remoteDataSource.getTransactions().getOrDefault(emptyList())
                val localTransactions = remoteTransactions.toEntityList()
                localDataSource.saveTransactions(localTransactions)
                Result.success(localTransactions)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun getTransactionById(id: Long): TransactionEntity? =
        withContext(ioDispatcher) {
            return@withContext localDataSource.getTransaction(id)
        }
}