package com.jdc.deno.projection.api.exceptions

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.context.annotation.Configuration
import org.springframework.validation.BindingResult

@Configuration
@Aspect
class ValidationResultAspect {

    @Pointcut("within(com.jdc.deno.projection.api.*)")
    fun apiMethods() {}

    @Before(value = "apiMethods() && args(.., result)", argNames = "result")
    fun checkResult(result:BindingResult) {
        if(result.hasErrors()) {
            throw ValidationResultException(result)
        }
    }
}