package com.jdc.deno.projection.api.exceptions

import com.jdc.deno.projection.model.dto.ErrorResponse
import com.jdc.deno.projection.model.dto.ErrorType
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BusinessErrorExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(exception: EntityNotFoundException):ErrorResponse {
        return ErrorResponse(ErrorType.Business, listOf(exception.message ?: "There is no entity with id."))
    }
}