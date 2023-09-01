package com.jdc.deno.projection.model.dto

enum class ErrorType {
    Validation, Business, Platform
}

data class ErrorResponse(
    val type:ErrorType,
    val messages:List<String>
)
