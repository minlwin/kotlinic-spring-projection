package com.jdc.deno.projection.api

import com.jdc.deno.projection.model.form.PricingPlanCreateForm
import com.jdc.deno.projection.model.form.PricingPlanSearchForm
import com.jdc.deno.projection.service.PricingPlanService
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pricing")
class PricingPlanApi (private val service:PricingPlanService){

    @GetMapping
    fun search(form:PricingPlanSearchForm,
               @RequestParam(required = false, defaultValue = "0") current:Int,
               @RequestParam(required = false, defaultValue = "10") max:Int)
        = service.search(form, current, max)

    @GetMapping("export")
    fun export(form: PricingPlanSearchForm) = service.searchForExport(form)

    @PostMapping
    fun create(@Validated @RequestBody form:PricingPlanCreateForm, result:BindingResult)
        = service.create(form)

    @GetMapping("{id}")
    fun findById(@PathVariable id:Long) = service.findById(id)

    @PutMapping("{id}")
    fun update(@PathVariable id:Long,
                 @Validated @RequestBody form:PricingPlanCreateForm, result: BindingResult)
        = service.update(id, form)

}