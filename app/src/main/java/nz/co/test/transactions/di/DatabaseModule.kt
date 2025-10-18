package nz.co.test.transactions.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nz.co.test.transactions.data.local.TransactionDao
import nz.co.test.transactions.data.local.TransactionDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTransactionDatabase(
        @ApplicationContext context: Context
    ): TransactionDatabase {
        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            TransactionDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: TransactionDatabase): TransactionDao {
        return database.transactionDao()
    }
}