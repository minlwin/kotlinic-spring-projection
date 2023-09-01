package com.jdc.deno.projection.api

import com.jdc.deno.projection.model.dto.MerchantDto
import com.jdc.deno.projection.model.form.MerchantCreateForm
import com.jdc.deno.projection.model.form.MerchantSearchForm
import com.jdc.deno.projection.service.MerchantService
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("merchants")
class MerchantApi(private val service: MerchantService) {
    @GetMapping
    fun search(form:MerchantSearchForm) = service.search(form)

    @PostMapping
    fun create(@RequestBody form:MerchantCreateForm, result:BindingResult):MerchantDto {
        return service.create(form)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id:Long):MerchantDto {
        return service.findById(id)
    }

    @PostMapping("{id}")
    fun update(@PathVariable id:Long, @RequestBody form:MerchantCreateForm, result: BindingResult):MerchantDto {
        return service.update(id, form)
    }
}