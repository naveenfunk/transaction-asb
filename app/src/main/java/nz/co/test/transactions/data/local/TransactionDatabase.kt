package nz.co.test.transactions.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import nz.co.test.transactions.data.local.entities.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DATABASE_NAME = "transaction_db"
    }
}