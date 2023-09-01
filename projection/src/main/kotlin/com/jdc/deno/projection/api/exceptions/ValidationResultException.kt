package com.jdc.deno.projection.api.exceptions

import org.springframework.validation.BindingResult

class ValidationResultException(result: BindingResult) : RuntimeException() {

    private var errors:List<String> = emptyList()

    val messages:List<String>
        get() = errors

    init {
        errors = result.fieldErrors.map { it.defaultMessage ?: "Invalid ${it.field}" } as List<String>
    }
}