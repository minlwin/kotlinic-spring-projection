package com.jdc.deno.projection.api

import com.jdc.deno.projection.model.form.PricingPlanSearchForm
import com.jdc.deno.projection.service.PricingPlanService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pricing")
class PricingPlanApi (private val service:PricingPlanService){

    @GetMapping
    fun search(form:PricingPlanSearchForm,
               @RequestParam(required = false, defaultValue = "0") current:Int,
               @RequestParam(required = false, defaultValue = "10") max:Int)
        = service.search(form, current, max)

}