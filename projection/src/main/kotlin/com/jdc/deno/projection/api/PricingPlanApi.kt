package com.jdc.deno.projection.api

import com.jdc.deno.projection.model.dto.PricingPlanListDto
import com.jdc.deno.projection.model.form.PricingPlanSearchForm
import com.jdc.deno.projection.service.PricingPlanService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pricing")
class PricingPlanApi (private val service:PricingPlanService){

    @GetMapping
    fun search(form:PricingPlanSearchForm):List<PricingPlanListDto> {
        return service.search(form)
    }
}