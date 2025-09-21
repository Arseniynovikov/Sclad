package com.example.sklad.domain.models

enum class TransactionType { INCOMING, OUTGOING }

data class TransactionModel(
    val id: Int,
    val productId: Int,
    val quantity: Int,
    val timestamp: Long,
    val type: TransactionType,
    val userId: Int
)
