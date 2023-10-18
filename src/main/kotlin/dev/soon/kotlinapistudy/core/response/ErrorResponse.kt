package dev.soon.kotlinapistudy.core.response

data class ErrorResponse(
    val message: String,
    val errorType: String = "Invalid Argument"
)
