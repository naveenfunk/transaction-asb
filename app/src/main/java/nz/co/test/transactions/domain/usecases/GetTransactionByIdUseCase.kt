package nz.co.test.transactions.domain.usecases

import nz.co.test.transactions.data.local.entities.TransactionEntity
import nz.co.test.transactions.data.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(id: String): TransactionEntity? {
        return transactionRepository.getTransactionById(id.toLong())
    }
}