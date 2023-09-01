package com.jdc.deno.projection.api.exceptions

import com.jdc.deno.projection.model.dto.ErrorResponse
import com.jdc.deno.projection.model.dto.ErrorType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidationResultExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(exception:ValidationResultException):ErrorResponse {
        return ErrorResponse(ErrorType.Validation, exception.messages)
    }
}