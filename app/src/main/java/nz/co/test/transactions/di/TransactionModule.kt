package nz.co.test.transactions.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import nz.co.test.transactions.data.local.TransactionLocalDataSource
import nz.co.test.transactions.data.local.TransactionLocalDataSourceImpl
import nz.co.test.transactions.data.remote.TransactionRemoteDataSource
import nz.co.test.transactions.data.remote.TransactionRemoteDataSourceImpl
import nz.co.test.transactions.data.remote.TransactionsService
import nz.co.test.transactions.data.repository.TransactionRepository
import nz.co.test.transactions.data.repository.TransactionRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionModule {

    @Binds
    abstract fun bindLocalTransactionDataSource(
        impl: TransactionLocalDataSourceImpl
    ): TransactionLocalDataSource

    @Binds
    abstract fun bindTransactionRepo(transactionRepository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    abstract fun bindTransactionRemoteDataSource(
        impl: TransactionRemoteDataSourceImpl
    ): TransactionRemoteDataSource

}