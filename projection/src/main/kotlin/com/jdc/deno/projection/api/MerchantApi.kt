package com.jdc.deno.projection.api

import com.jdc.deno.projection.model.form.MerchantSearchForm
import com.jdc.deno.projection.service.MerchantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("merchants")
class MerchantApi(private val service: MerchantService) {

    @GetMapping
    fun search(form:MerchantSearchForm) = service.search(form)
}