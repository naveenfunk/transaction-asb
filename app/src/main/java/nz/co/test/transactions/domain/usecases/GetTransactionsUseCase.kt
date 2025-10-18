package nz.co.test.transactions.domain.usecases

import nz.co.test.transactions.data.local.entities.TransactionEntity
import nz.co.test.transactions.data.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(): Result<List<TransactionEntity>> {
        return transactionRepository.getTransactions()
    }
}