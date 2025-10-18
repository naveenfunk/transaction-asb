package nz.co.test.transactions.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import nz.co.test.transactions.data.local.entities.TransactionEntity

@JsonClass(generateAdapter = true)
data class Transaction(
    @Json(name = "id") val id: Long,
    @Json(name = "transactionDate") val transactionDate: String,
    @Json(name = "summary") val summary: String,
    @Json(name = "debit") val debit: String,
    @Json(name = "credit") val credit: String
)

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        transactionDate = this.transactionDate,
        summary = this.summary,
        debit = this.debit,
        credit = this.credit
    )
}

fun List<Transaction>.toEntityList(): List<TransactionEntity> {
    return this.map { it.toEntity() }
}