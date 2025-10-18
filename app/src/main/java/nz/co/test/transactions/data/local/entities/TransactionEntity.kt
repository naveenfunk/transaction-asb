package nz.co.test.transactions.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,

    val transactionDate: String,
    val summary: String,
    val debit: String,
    val credit: String
)