package dev.soon.kotlinapistudy.core.exception

class InvalidInputException(
    message: String = "Invalid Input"
) : RuntimeException(message)