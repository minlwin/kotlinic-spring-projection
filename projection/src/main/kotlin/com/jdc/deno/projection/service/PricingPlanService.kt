package com.jdc.deno.projection.service

import com.jdc.deno.projection.model.dto.PricingPlanListDto
import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.form.PricingPlanSearchForm
import com.jdc.deno.projection.model.repo.PricingPlanRepo
import org.springframework.stereotype.Service

@Service
class PricingPlanService(private val repo:PricingPlanRepo) {

    fun search(form: PricingPlanSearchForm): List<PricingPlanListDto> {
        return repo.findAll {
            val query = it.createQuery(PricingPlanListDto::class.java)
            val root = query.from(PricingPlan::class.java)

            PricingPlanListDto.select(query, root)
            query.where(form.where(it, root))

            query
        }
    }


}